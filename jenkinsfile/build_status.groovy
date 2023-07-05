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
    }
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