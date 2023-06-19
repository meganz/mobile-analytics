plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(project(":analytics-annotations"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
dependencies{
//    add("kspCommonMain", project(":analytics-processor"))
    add("kspCommonMainMetadata", project(":analytics-processor"))
//    add("kspAndroid", project(":analytics-processor"))
//    add("kspAndroidTest", project(":analytics-processor"))
//    add("kspIosX64", project(":analytics-processor"))
//    add("kspIosX64Test", project(":analytics-processor"))
//    add("kspIosArm64", project(":analytics-processor"))
//    add("kspIosArm64Test", project(":analytics-processor"))
//    add("kspIosSimulatorArm64", project(":analytics-processor"))
//    add("kspIosSimulatorArm64Test", project(":analytics-processor"))
}

android {
    namespace = "mega.privacy.mobile.analytics"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
