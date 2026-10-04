import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    sourceSets {
        jvmMain {
            dependencies {
                implementation(project(":analytics-core"))
                implementation(project(":analytics-annotations"))
                implementation(libs.kotlinpoet)
                implementation(libs.kotlinpoet.ksp)
                implementation(libs.google.ksp)
                implementation(libs.kotlinx.serialization)
            }
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }

        jvmTest {
            // kotlin-compile-testing exposes the compiler API behind this opt-in
            languageSettings.optIn("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
            dependencies {
                implementation(project.dependencies.platform(libs.junit.bom))
                implementation(libs.bundles.junit5)
                runtimeOnly(libs.junit.platform.launcher)
                implementation(libs.bundles.mocking)
                implementation(libs.bundles.compile.testing)
            }
        }
    }
}
