import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
    id("com.jfrog.artifactory").version("4.32.0").apply(false)
    `maven-publish`
}

allprojects {
    group = "mega.privacy.mobile"
    version = "1.0.0"
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val androidLibVersion: String by extra {
    val now = OffsetDateTime.now(java.time.ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss")
    now.format(formatter)
}
