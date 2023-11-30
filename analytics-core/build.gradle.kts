plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.jfrog.artifactory")
    `maven-publish`
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    jvm()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        publishLibraryVariants("release", "debug")
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
            }
        }
    }

    publishing {
        publications {
            matching { it.name == "${android().name}kotlinMultiplatform" }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
            }

            val libVersion = rootProject.extra.get("androidLibVersion") as String
            create<MavenPublication>("aar") {
                groupId = "mega.privacy.mobile"
                artifactId = "analytics-core-android"
                version = libVersion
                artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                artifact("$buildDir/libs/${project.name}-android-1.0.0-sources.jar") {
                    classifier = "sources"
                    extension = "jar"
                }
            }
        }
    }
}

android {
    namespace = "mega.privacy.mobile.analytics.core"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

artifactory {
    clientConfig.isIncludeEnvVars = true
    setContextUrl("https://artifactory.developers.mega.co.nz/artifactory/mega-gradle")
    publish {
        repository {
            setRepoKey("mobile-analytics")
            setUsername(System.getenv("ARTIFACTORY_USER")) // The publisher user name
            setPassword(System.getenv("ARTIFACTORY_ACCESS_TOKEN")) // The publisher password
        }
        defaults {
            setPublishArtifacts(true)
            publications("aar")
            setPublishPom(true)
        }
    }
}

tasks.getByName("artifactoryPublish") {
    dependsOn("assemble")
    dependsOn("androidReleaseSourcesJar")
}
