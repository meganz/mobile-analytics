BUILD_STEP = ''

/**
 * GitLab commands that can trigger this job.
 */
BUILD_KMM_ANALYTICS_CMD = "build_kmm_analytics"
BUILD_KA_CMD = "build_ka"

// The log file of publishing lib to Artifactory
ARTIFACTORY_PUBLISH_LOG = "artifactory_publish.log"

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
                downloadJenkinsConsoleLog(CONSOLE_LOG_FILE)
                String jenkinsLogLink = uploadFileToGitLab(CONSOLE_LOG_FILE)
                String message = failureMessage("<br/>") +
                        "<br/>Build Log:\t${jenkinsLogLink}"

                sendToMR(message)
                slackSend color: 'danger', message: failureMessage("\n")
                slackUploadFile filePath: 'console.txt', initialComment: 'Jenkins Log'
            }
        }
        success {
            script {
                sendToMR(successMessage("<br/>"))
                slackSend color: "good", message: successMessage("\n")
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
                    println("Print environment variables")
                    sh('set')
                }
            }
        }
        stage('Publish Library to Artifactory') {
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
                            sendToMR("Publish to artifactory - started")

                            sh """
                                ./gradlew artifactoryPublish 2>&1  | tee ${ARTIFACTORY_PUBLISH_LOG}
                            """
                        }
                    }
                }
            }
        }
    }
}

/**
 * send message to GitLab MR comment
 * @param message message to send
 */
void sendToMR(String message) {
    println("####### Entering sendToMR() #######")

    def mrNumber = getMrNumberInCD()

    if (mrNumber != null && !mrNumber.isEmpty()) {
        withCredentials([usernamePassword(credentialsId: 'Gitlab-Access-Token', usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            env.MARKDOWN_LINK = message
            env.MERGE_REQUEST_URL = "${env.GITLAB_BASE_URL}/api/v4/projects/278/merge_requests/${mrNumber}/notes"
            sh 'curl --request POST --header PRIVATE-TOKEN:$TOKEN --form body=\"${MARKDOWN_LINK}\" ${MERGE_REQUEST_URL}'
        }
    } else {
        println("[Failure] Cannot get MR ID!")
    }
}

/**
 * Get MergeRequest ID for CD
 *
 * @return MR Number if job is triggered in CD. Otherwise return null.
 */
def getMrNumberInCD() {
    println("####### Entering getMrNumberInCD() #######")
    return env.gitlabMergeRequestIid
}

/**
 * Success message, which might be used for Slack or GitLab MR.
 * @param lineBreak Slack and MR comment use different line breaks. Slack uses "/n"
 * while GitLab MR uses "<br/>".
 * @return The success message to be sent
 */
private String successMessage(String lineBreak) {
    return ":rocket: Mobile-Analytics library(Android) has been published successfully!(Build Number: ${env.BUILD_NUMBER})" +
            "${lineBreak}Author:\t${gitlabCommentAuthor}" +
            "${lineBreak}Commit:\t${GIT_COMMIT}" +
            "${lineBreak}Version:\tmega.privacy.mobile:analytics-events-android:${getVersionText()}" +
            "${lineBreak}Command:\t${gitlabTriggerPhrase}" +
            "${lineBreak}AAR Artifactory Page: ${getLibArtifactoryUrl()}"
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
    String message = ":x: Mobile-Analytics Library(Android) Creation Failed!(BuildNumber: ${env.BUILD_NUMBER})" +
            "${lineBreak}Author:\t${gitlabCommentAuthor}" +
            "${lineBreak}Command:\t${gitlabTriggerPhrase}" +
            "${lineBreak}Commit:\t${GIT_COMMIT}"
    return message
}