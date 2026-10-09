import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.kmp.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.jfrog) apply false
    alias(libs.plugins.google.ksp) apply false
    `maven-publish`
}

allprojects {
    group = "mega.privacy.mobile"
    version = "1.0.0"
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

// A local repository so CI can run the same publishing tasks as Artifactory without uploading:
// ./gradlew publishAllPublicationsToVerifyRepository
subprojects {
    plugins.withId("maven-publish") {
        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    name = "verify"
                    url = uri(rootProject.layout.buildDirectory.dir("verify-repo"))
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

// Timestamp based version used for the published Android artifacts
extra["androidLibVersion"] = OffsetDateTime.now(java.time.ZoneOffset.UTC)
    .format(DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss"))
