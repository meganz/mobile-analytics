import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.jfrog)
    `maven-publish`
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    android {
        namespace = "mega.privacy.mobile.analytics.core"
        compileSdk = 36
        minSdk = 26
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosX64(),
        macosArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "analytics-core"
        }
    }

    mingwX64("windows") {
        binaries {
            sharedLib {
                baseName = "analytics-core"
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

// AGP registers the Android bundle/sources tasks after evaluation, so the publication must be
// declared once they exist.
afterEvaluate {
    publishing.publications {
        val libVersion = rootProject.extra.get("androidLibVersion") as String
        create<MavenPublication>("aar") {
            groupId = "mega.privacy.mobile"
            artifactId = "analytics-core-android"
            version = libVersion
            artifact(tasks.named("bundleAndroidMainAar"))
            artifact(tasks.named("androidSourcesJar")) {
                classifier = "sources"
                extension = "jar"
            }
        }
    }
}

artifactory {
    setContextUrl("https://artifactory.developers.mega.co.nz/artifactory/mega-gradle")
    publish {
        repository {
            repoKey = "mobile-analytics"
            username = System.getenv("ARTIFACTORY_USER") // The publisher user name
            password = System.getenv("ARTIFACTORY_ACCESS_TOKEN") // The publisher password
        }
        defaults {
            setPublishArtifacts(true)
            publications("aar")
            setPublishPom(true)
        }
    }
}

tasks.named("artifactoryPublish") {
    dependsOn("assemble")
    dependsOn("androidSourcesJar")
}
