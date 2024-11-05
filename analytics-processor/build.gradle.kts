
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

        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":analytics-core"))
                implementation(project(":analytics-annotations"))
                implementation("com.squareup:kotlinpoet:1.14.2")
                implementation("com.squareup:kotlinpoet-ksp:1.14.2")
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
                implementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
                implementation("org.mockito:mockito-inline:5.2.0")
                implementation("com.google.truth:truth:1.1.3")
                implementation("com.github.tschuchortdev:kotlin-compile-testing:1.5.0")
                implementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.5.0")
            }
        }
    }
}