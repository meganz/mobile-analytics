@Library('jenkins-android-shared-lib') _

BUILD_STEP = ''

// The log file of publishing lib to Artifactory
ARTIFACTORY_PUBLISH_LOG = "artifactory_publish.log"

// Flag indicating whether generated Swift package has updates.
HAS_SWIFT_PACKAGE_CHANGE = false

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

                slackSend color: 'danger', message: failureMessage("\n")
                slackUploadFile filePath: 'console.txt', initialComment: 'Jenkins Log'
            }
        }
        success {
            script {
                common = load('jenkinsfile/common.groovy')

                createTag(getAndroidLibVersion())
                slackSend color: "good", message: androidSuccessMessage("\n")

                if (HAS_SWIFT_PACKAGE_CHANGE) {
                    slackSend color: "good", message: iOSSuccessMessage("\n")
                } else {
                    String noFileChangeMsg = "No change in iOS Swift Package. No files are published."
                    slackSend color: "good", message: noFileChangeMsg
                }
            }
        }
        cleanup {
            cleanWs(cleanWhenFailure: true)
        }
    }
    stages {
        stage('prepare') {
            steps {
                script {
                    common = load('jenkinsfile/common.groovy')
                    println("Print environment variables")
                    sh('set')
                    // Only code that is merged to main may be published, because every publish
                    // releases the library to the apps and tags main.
                    if (env.gitlabActionType != "PUSH" || env.gitlabTargetBranch != "main") {
                        error("Publishing runs only on a push to main " +
                                "(action: ${env.gitlabActionType}, branch: ${env.gitlabTargetBranch})")
                    }
                }
            }
        }
        stage('Verify Before Publishing') {
            steps {
                script {
                    BUILD_STEP = 'Verify Before Publishing'
                    sh "./gradlew build"
                    // The build regenerates the JSON files, so any difference means they were not committed
                    sh "git diff --exit-code -- shared/src/commonMain/resources"
                    String baseline = previousPublishedTag()
                    String allowRemoval = isEventIdRemovalAllowed(baseline) ? "-PallowEventIdRemoval=true" : ""
                    sh "./gradlew :shared:verifyEventIdStability -PeventIdBaseline=${baseline} ${allowRemoval}"
                }
            }
        }
        stage('Publish Android Library to Artifactory') {
            steps {
                script {
                    BUILD_STEP = 'Publish to artifactory'

                    withCredentials([
                            string(credentialsId: 'ARTIFACTORY_USER', variable: 'ARTIFACTORY_USER'),
                            string(credentialsId: 'ARTIFACTORY_ACCESS_TOKEN', variable: 'ARTIFACTORY_ACCESS_TOKEN'),
                    ]) {
                        withEnv([
                                "ARTIFACTORY_USER=${ARTIFACTORY_USER}",
                                "ARTIFACTORY_ACCESS_TOKEN=${ARTIFACTORY_ACCESS_TOKEN}",
                        ]) {
                            sh """
                                ./gradlew artifactoryPublish 2>&1  | tee ${ARTIFACTORY_PUBLISH_LOG}
                            """
                        }
                    }
                }
            }
        }
        stage('Publish iOS library to GitLab and GitHub') {
            steps {
                script {
                    BUILD_STEP = 'Publish to artifactory'

                    util.useGitLab() {
                        util.useGpg() {
                            sh """
                                ./gradlew createSwiftPackage :shared:verifySwiftPackageEvents
                                (cd ios-smoke-tests && swift test)
                                rm -fr mobile-analytics-ios
                                git clone https://code.developers.mega.co.nz/mobile/kmm/mobile-analytics-ios.git
                                cd mobile-analytics-ios
                                cp -fr ../SwiftPackages/MEGAAnalyticsiOS/MEGAAnalyticsiOS.xcframework .
                                cp -fr ../SwiftPackages/MEGAAnalyticsiOS/Package.swift .
                            """
                            if (hasSwiftPackageChanges()) {
                                println("there are new file changes of SwiftPackage. Starting to push changes...")
                                HAS_SWIFT_PACKAGE_CHANGE = true
                                sh """
                                cd mobile-analytics-ios
                                git config http.postBuffer 100000000
                                git add .
                                git commit -m "iOS analytics update - author(${authorName()}) commit(${GIT_COMMIT})"
                                git push
                            """

                                // push GitLab to GitHub
                                withCredentials([
                                        usernamePassword(credentialsId: 'GitHub-Access-Token',
                                                usernameVariable: 'USERNAME',
                                                passwordVariable: 'TOKEN')
                                ]) {
                                    sh """
                                    cd mobile-analytics-ios
                                    git push https://${USERNAME}:${TOKEN}@github.com/meganz/mobile-analytics-ios.git HEAD:main
                                """
                                }
                            } else {
                                HAS_SWIFT_PACKAGE_CHANGE = false
                                println("There are no file changes of SwiftPackage")
                            }
                        }
                    }
                }
            }
        }
        stage('Sync Code to GitHub') {
            steps {
                script {
                    BUILD_STEP = 'Sync Code to GitHub'

                    withCredentials([
                            usernamePassword(credentialsId: 'GitHub-Access-Token',
                                    usernameVariable: 'USERNAME',
                                    passwordVariable: 'TOKEN')
                    ]) {
                        sh """
                            git push https://${USERNAME}:${TOKEN}@github.com/meganz/mobile-analytics.git HEAD:main
                        """
                    }
                }
            }
        }
    }
}

private void createTag(String tag) {
    common = load('jenkinsfile/common.groovy')
    common.createTag(tag)
}

/**
 * Get the name of the person who pushed to main.
 * @return name
 */
private String authorName() {
    return env.gitlabUserName
}

/**
 * Find the version tag of the previous publish, which is the baseline for the event ID check.
 * Only tags in the history of HEAD are used, so that a tag created by a concurrent build for a
 * newer commit is never the baseline. The build fails when no tag is found, because publishing
 * without the check could release changed event IDs.
 * @return the tag name, in the format yyyyMMdd.HHmmss
 */
private String previousPublishedTag() {
    fetchTags()
    String tag = sh(
            script: "git tag --list --merged HEAD --sort=-v:refname | grep -E '^[0-9]{8}\\.[0-9]{6}\$' | head -n 1",
            returnStdout: true
    ).trim()
    if (tag.isEmpty()) {
        error("No previous version tag found in the history of HEAD. Cannot verify event IDs before publishing.")
    }
    println("Event ID baseline = $tag")
    return tag
}

/**
 * Fetch the version tags. Plain `sh` has no GitLab credentials, so pass them through a credential
 * helper. Single-quoted so the token is never interpolated into the script or written to the git config.
 */
private void fetchTags() {
    withCredentials([usernamePassword(credentialsId: 'Gitlab-Access-Token', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
        sh '''
            GIT_TERMINAL_PROMPT=0 git -c credential.helper= \
                -c credential.helper='!f() { echo "username=${GIT_USERNAME}"; echo "password=${GIT_PASSWORD}"; }; f' \
                fetch --tags origin
        '''
    }
}

/**
 * Event removals are allowed when a commit since [baseline] contains [allow-event-id-removal] in
 * its message, so that a removal accepted in the MR pipeline does not block the publish.
 */
private boolean isEventIdRemovalAllowed(String baseline) {
    return sh(
            script: "git log ${baseline}..HEAD --format=%B | grep -qF '[allow-event-id-removal]'",
            returnStatus: true
    ) == 0
}

/**
 * Read the swift package commit ID
 * @return
 */
String getSwiftPackageCommit() {
    return sh(script: "cd mobile-analytics-ios ; git rev-parse HEAD", returnStdout: true).trim()
}

/**
 * Check if there is file changes in git workspace in Swift Package repo.
 * @return true if there is change. Otherwise return false.
 */
boolean hasSwiftPackageChanges() {
    String output = sh(script: "cd mobile-analytics-ios ; git status --porcelain", returnStdout: true).trim()
    println("hasSwiftPackageChanges: " + output)
    return !output.isEmpty()
}

/**
 * Success message for iOS publishing.
 * @param lineBreak Slack and MR comment use different line breaks. Slack uses "/n",
 * while GitLab MR uses "<br/>".
 * @return
 */
boolean iOSSuccessMessage(String lineBreak) {
    return ":rocket: Mobile-Analytics library(iOS) has been published successfully!(Build Number: ${env.BUILD_NUMBER})" +
            "${lineBreak}Author:\t${authorName()}" +
            "${lineBreak}Commit:\t${GIT_COMMIT}" +
            "${lineBreak}SwiftPackage Commit:\t${getSwiftPackageCommit()}"
}

/**
 * Success message, which might be used for Slack or GitLab MR.
 * @param lineBreak Slack and MR comment use different line breaks. Slack uses "/n"
 * while GitLab MR uses "<br/>".
 * @return The success message to be sent
 */
private String androidSuccessMessage(String lineBreak) {
    return ":rocket: Mobile-Analytics library(Android) has been published successfully!(Build Number: ${env.BUILD_NUMBER})" +
            "${lineBreak}Author:\t${authorName()}" +
            "${lineBreak}Commit:\t${GIT_COMMIT}" +
            "${lineBreak}Version:\tmega.privacy.mobile:analytics-events-android:${getAndroidLibVersion()}" +
            "${lineBreak}AAR Artifactory Page: ${getLibArtifactoryUrl()}"
}

private String getAndroidLibVersion() {
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
 * download jenkins build console log and save to file.
 */
void downloadJenkinsConsoleLog(String targetFile) {
    withCredentials([usernameColonPassword(credentialsId: 'Jenkins-Login', variable: 'CREDENTIALS')]) {
        sh "curl -u $CREDENTIALS ${BUILD_URL}/consoleText -o ${targetFile}"
    }
}

/**
 * Upload file to GitLab and return the GitLab link
 * @param fileName the local file to be uploaded.
 * @return file link on GitLab
 */
String uploadFileToGitLab(String fileName) {
    String link = ""
    withCredentials([usernamePassword(credentialsId: 'Gitlab-Access-Token', usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
        final String response = sh(script: "curl -s --request POST --header PRIVATE-TOKEN:$TOKEN --form file=@${fileName} ${env.GITLAB_BASE_URL}/api/v4/projects/278/uploads", returnStdout: true).trim()
        link = new groovy.json.JsonSlurperClassic().parseText(response).markdown
        return link
    }
    return link
}

/**
 * Create the build report of failed lib build
 *
 * @param lineBreak the line break used between the lines. For GitLab and Slack, different line break
 * can be provided. GitLab accepts HTML "<BR/>", and Slack accepts "\n"
 * @return failure message
 */
private String failureMessage(String lineBreak) {
    String message = ":x: Mobile-Analytics Library(Android+iOS) Creation Failed!(Build No:${env.BUILD_NUMBER})" +
            "${lineBreak}Author:\t${authorName()}" +
            "${lineBreak}Commit:\t${GIT_COMMIT}"
    return message
}