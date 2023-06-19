import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

val kspVersion: String by project

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.21"
}

kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":analytics-annotations"))
                implementation("com.squareup:javapoet:1.13.0")
                implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            }
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }

        val jvmTest by getting {
            dependencies {
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
                implementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
                implementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
                implementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
                implementation("org.mockito:mockito-inline:3.11.2")
                implementation("com.google.truth:truth:1.1.3")
            }
        }
    }
}