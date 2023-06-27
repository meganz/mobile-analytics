import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

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
                jvmTarget = "17"
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

    targets.withType<KotlinNativeTarget>{
        binaries.withType<Framework> {
            isStatic = false
            export(project(":analytics-annotations"))
            export(project(":analytics-core"))

            transitiveExport = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(project(":analytics-annotations"))
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
dependencies{
//    add("kspCommonMain", project(":analytics-processor"))
    add("kspCommonMainMetadata", project(":analytics-processor"))
    add("kspIosArm64", project(":analytics-processor"))
    add("kspIosX64", project(":analytics-processor"))
    add("kspIosSimulatorArm64", project(":analytics-processor"))
    add("kspAndroid", project(":analytics-processor"))

//    add("kspAndroidTest", project(":analytics-processor"))
//    add("kspIosX64", project(":analytics-processor"))
//    add("kspIosX64Test", project(":analytics-processor"))
//    add("kspIosArm64Test", project(":analytics-processor"))
//    add("kspIosSimulatorArm64Test", project(":analytics-processor"))
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
