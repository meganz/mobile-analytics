import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.jfrog) apply false
    alias(libs.plugins.google.ksp) apply false
    `maven-publish`
}

buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
    }
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

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val androidLibVersion: String by extra {
    val now = OffsetDateTime.now(java.time.ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss")
    now.format(formatter)
}
