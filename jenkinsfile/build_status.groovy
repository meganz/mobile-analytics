BUILD_STEP = ''

/**
 * GitLab commands that can trigger this job.
 */
BUILD_KMM_ANALYTICS_CMD = "build_kmm_analytics"
BUILD_KA_CMD = "build_ka"

// The log file of publishing lib to Artifactory
ARTIFACTORY_PUBLISH_LOG = "artifactory_publish.log"


/**
 * common.groovy file with common methods
 */
def common

pipeline {
    agent { label 'mac-jenkins-slave-android || mac-jenkins-slave' }
    options {
        // Stop the build early in case of compile or test failures
        skipStagesAfterUnstable()
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '1'))
        timeout(time: 1, unit: 'HOURS')
        gitLabConnection('GitLabConnection')
    }
    environment {
        LC_ALL = 'en_US.UTF-8'
        LANG = 'en_US.UTF-8'

        NDK_ROOT = '/opt/buildtools/android-sdk/ndk/21.3.6528147'
        JAVA_HOME = '/opt/buildtools/zulu17.42.19-ca-jdk17.0.7-macosx'
        ANDROID_HOME = '/opt/buildtools/android-sdk'

        PATH = "/opt/buildtools/android-sdk/cmake/3.22.1/bin:/Applications/MEGAcmd.app/Contents/MacOS:/opt/buildtools/zulu17.42.19-ca-jdk17.0.7-macosx/bin:/opt/brew/bin:/opt/brew/opt/gnu-sed/libexec/gnubin:/opt/brew/opt/gnu-tar/libexec/gnubin:/opt/buildtools/android-sdk/platform-tools:/opt/buildtools/android-sdk/build-tools/30.0.3:$PATH"

        CONSOLE_LOG_FILE = 'console.txt'
    }
    post {
        failure {
            script {
                common = load('jenkinsfile/common.groovy')

                common.downloadJenkinsConsoleLog(CONSOLE_LOG_FILE)
                String jenkinsLogLink = common.uploadFileToGitLab(CONSOLE_LOG_FILE)

                if (common.hasGitLabMergeRequest()) {
                    def failureMessage = failureMessage("<br/>") +
                            "<br/>Build Log: ${jenkinsLogLink}"
                    common.sendToMR(failureMessage)

                    slackSend color: 'danger', message: failureMessage("\n")
                    slackUploadFile filePath: 'console.txt', initialComment: 'Mobile Analytics build Log'
                } else {
                    comment = ":x: Mobile Analytics Build failed for branch: ${env.GIT_BRANCH} \nMR Link:${env.CHANGE_URL}"
                    slackSend color: "danger", message: comment
                    slackUploadFile filePath: "console.txt", initialComment: "Mobile Analytics build Log"
                }
            }
        }
        success {
            script {
                common = load('jenkinsfile/common.groovy')

                String mergeRequestMessage = ":white_check_mark: Build Succeeded!\n\n" +
                        "**Last Commit:** (${env.GIT_COMMIT})" + getLastCommitMessage()

                common.sendToMR(mergeRequestMessage)
            }
        }
        cleanup {
            cleanWs(cleanWhenFailure: true)
        }
    }
    stages {
        stage('prepare') {
            steps {
                gitlabCommitStatus(name: 'Preparation') {
                    script {
                        println("Print environment variables")
                        sh('set')
                    }
                }

            }
        }
        stage('Verify Build') {
            steps {
                gitlabCommitStatus(name: 'Preparation') {
                    script {
                        BUILD_STEP = 'Publish to artifactory'
                        sh "./gradlew build"
                    }
                }
            }
        }
        stage('Verify Event IDs') {
            steps {
                gitlabCommitStatus(name: 'Event IDs') {
                    script {
                        BUILD_STEP = 'Verify Event IDs'
                        // The build regenerates the JSON files, so any difference means they were not committed
                        sh "git diff --exit-code -- shared/src/commonMain/resources"
                        fetchMainBranch()
                        String allowRemoval = isEventIdRemovalAllowed() ? "-PallowEventIdRemoval=true" : ""
                        sh "./gradlew :shared:verifyEventIdStability -PeventIdBaseline=origin/main ${allowRemoval}"
                    }
                }
            }
        }
        stage('Verify Publishing') {
            steps {
                gitlabCommitStatus(name: 'Publishing') {
                    script {
                        BUILD_STEP = 'Verify Publishing'
                        // Runs the publishing tasks against a local repository; nothing is uploaded
                        sh "./gradlew publishAllPublicationsToVerifyRepository"
                    }
                }
            }
        }
        stage('Verify iOS Package') {
            steps {
                gitlabCommitStatus(name: 'iOS Package') {
                    script {
                        BUILD_STEP = 'Verify iOS Package'
                        // A separate Gradle invocation, as in the publish job
                        sh "./gradlew createSwiftPackage :shared:verifySwiftPackageEvents"
                        sh "cd ios-smoke-tests && swift test"
                    }
                }
            }
        }
    }
}

/**
 * Fetch main as origin/main, the baseline for the event ID check. The MR checkout only fetches
 * the MR ref, and plain `sh` has no GitLab credentials, so pass them through a credential helper.
 * Single-quoted so the token is never interpolated into the script or written to the git config.
 */
private void fetchMainBranch() {
    withCredentials([usernamePassword(credentialsId: 'Gitlab-Access-Token', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
        sh '''
            GIT_TERMINAL_PROMPT=0 git -c credential.helper= \
                -c credential.helper='!f() { echo "username=${GIT_USERNAME}"; echo "password=${GIT_PASSWORD}"; }; f' \
                fetch --no-tags origin +refs/heads/main:refs/remotes/origin/main
        '''
    }
}

/**
 * Event removals are allowed when a commit on the branch contains [allow-event-id-removal]
 * in its message, for deliberate clean-ups of unused events.
 */
private boolean isEventIdRemovalAllowed() {
    return sh(
            script: "git log origin/main..HEAD --format=%B | grep -qF '[allow-event-id-removal]'",
            returnStatus: true
    ) == 0
}


private String failureMessage(String lineBreak) {
    return ":x: Build Failed" +
            "<br/>Last Commit Message: ${getLastCommitMessage()}" +
            "Last Commit ID: ${env.GIT_COMMIT}"

}

private String getVersionText() {
    println("######## Entering getVersionText() ########")

    String content = sh(script: "grep -e 'Deploying artifact.*\\.aar\$' ${ARTIFACTORY_PUBLISH_LOG}", returnStdout: true).trim()
    String[] lines = content.split("\n")
    // Example Line
    // [pool-24-thread-1] Deploying artifact: https://artifactory.developers.mega.co.nz/artifactory/mega-gradle/mobile-analytics/mega/privacy/mobile/analytics-annotations-android/20230630.015955/analytics-annotations-android-20230630.015955.aar
    for (line in lines) {
        println("parsing line = $line")
        String[] parts = line.split("/")
        String version = parts[parts.size() - 2]
        println("Library version = $version")
        return version
    }
    return "N/A"
}


/**
 * The web page of generated library
 * @return url link
 */
private String getLibArtifactoryUrl() {
    return "https://artifactory.developers.mega.co.nz/ui/repos/tree/General/mega-gradle/mobile-analytics/mega/privacy/mobile/analytics-events-android"
}

/**
 * Fetch the message of the last commit from environment variable.
 *
 * @return The commit message text if GitLab plugin has sent a valid commit message, which is
 * denoted as a Code Block in Gitlab.
 *
 * Otherwise, return a Bold "N/A" normally when CI build is triggered by MR comment "jenkins rebuild".
 */
String getLastCommitMessage() {
    println("entering getLastCommitMessage()")
    def lastCommitMessage = env.GITLAB_OA_LAST_COMMIT_MESSAGE
    if (lastCommitMessage == null) {
        return ""
    } else {
        // use markdown backticks to format commit message into a code block
        return "\n```\n$lastCommitMessage\n```\n".stripIndent().stripMargin()
    }
}