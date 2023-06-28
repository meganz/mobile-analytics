import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("digital.wup.android-maven-publish")
    `maven-publish`
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = false
            export(project(":analytics-annotations"))
            export(project(":analytics-core"))

            transitiveExport = true
        }
    }

    afterEvaluate {
        tasks {
            withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>> {
                if (name != "kspCommonMainKotlinMetadata")
                    dependsOn("kspCommonMainKotlinMetadata")
            }


            getByName("androidReleaseSourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("androidDebugSourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("iosArm64SourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("iosArm64SourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("iosSimulatorArm64SourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("iosX64SourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }

            getByName("sourcesJar") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }

        task("sourceJar") {
            dependsOn("kspCommonMainKotlinMetadata")
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
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain")
            dependencies {
                //put your multiplatform dependencies here
                compileOnly(project(":analytics-annotations"))
                implementation(project(":analytics-core"))
                api(project(":analytics-annotations"))
                api(project(":analytics-core"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

}
dependencies {
    add("kspCommonMainMetadata", project(":analytics-processor"))
}

android {
    namespace = "mega.privacy.mobile.analytics"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

ksp {
    // ...

    val relativeResourcePath = "src/commonMain/resources"

    val absoluteResourcePath = File(projectDir, relativeResourcePath).absolutePath

    arg("resourcePath", absoluteResourcePath)
}
