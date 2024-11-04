import groovy.util.Node
import org.gradle.kotlin.dsl.jvm
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import src.main.kotlin.HtmlTableTask

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("io.github.luca992.multiplatform-swiftpackage") version "2.1.2"
    id("com.jfrog.artifactory")
    `maven-publish`
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    androidTarget {
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
        macosArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "MEGAAnalyticsiOS"
        }
    }

    mingwX64("windows") {
        binaries {
            sharedLib {
                baseName = "MEGAAnalyticsWindows"
            }
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

            val libVersion = rootProject.extra.get("androidLibVersion") as String
            create<MavenPublication>("aar") {
                groupId = "mega.privacy.mobile"
                artifactId = "analytics-events-android"
                version = libVersion
                artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                artifact("$buildDir/libs/${project.name}-android-1.0.0-sources.jar") {
                    classifier = "sources"
                    extension = "jar"
                }

                pom.withXml {
                    addDependencyInPOM(libVersion)
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain")
            dependencies {
                //put your multiplatform dependencies here
                implementation(project(":analytics-annotations"))
                api(project(":analytics-annotations"))
                api(project(":analytics-core"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val windowsMain by getting {
            dependsOn(commonMain)
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

multiplatformSwiftPackage {
    packageName("MEGAAnalyticsiOS")
    zipFileName("MEGAAnalyticsiOS")
    distributionMode { local() }
    swiftToolsVersion("5.8")
    outputDirectory(File(projectDir, "../SwiftPackages/MEGAAnalyticsiOS"))
    targetPlatforms {
        iOS { v("14") }
        macOS { v("12")}
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

/**
 * Update POM to add dependency to core and annotation modules
 */
fun XmlProvider.addDependencyInPOM(libVersion: String) {
    val depRoot = asNode().appendNode("dependencies")
    val coreDependency = depRoot.appendNode("dependency")

    Node(coreDependency, "groupId").apply { setValue("mega.privacy.mobile") }
    Node(coreDependency, "artifactId").apply { setValue("analytics-core-android") }
    Node(coreDependency, "version").apply { setValue(libVersion) }
    Node(coreDependency, "scope").apply { setValue("compile") }

    val annotationsDependency = depRoot.appendNode("dependency")
    Node(annotationsDependency, "groupId").apply { setValue("mega.privacy.mobile") }
    Node(annotationsDependency, "artifactId").apply { setValue("analytics-annotations-android") }
    Node(annotationsDependency, "version").apply { setValue(libVersion) }
    Node(annotationsDependency, "scope").apply { setValue("compile") }

    val kotlinxSerializationJsonDep = depRoot.appendNode("dependency")
    Node(kotlinxSerializationJsonDep, "groupId").apply { setValue("org.jetbrains.kotlinx") }
    Node(kotlinxSerializationJsonDep, "artifactId").apply { setValue("kotlinx-serialization-json") }
    Node(kotlinxSerializationJsonDep, "version").apply { setValue("1.5.1") }
    Node(kotlinxSerializationJsonDep, "scope").apply { setValue("compile") }
}

tasks.getByName("artifactoryPublish") {
    dependsOn("assemble")
    dependsOn("androidReleaseSourcesJar")
}


tasks.register<HtmlTableTask>("generateHtmlTables")

// https://youtrack.jetbrains.com/issue/KT-42276
val workAroundKt43094 = true
if (workAroundKt43094) {
    fun stripLines(dotHFile: File) {
        val linesToStrip = listOf(
            """__attribute__((swift_name("KotlinChar.Companion")))""",
            """__attribute__((swift_name("KotlinString.Companion")))""",
            """__attribute__((swift_name("KotlinDuration.Companion")))""",
        )

        val dotHOriginal = dotHFile.readText()
        var dotHRewritten = dotHOriginal
        for (lineToStrip in linesToStrip) {
            dotHRewritten = dotHRewritten.replace(
                lineToStrip,
                "/* Stripped for KT-43094: $lineToStrip */"
            )
        }

        if (dotHRewritten == dotHOriginal) {
            logger.warn("Failed to strip swift_name lines from $dotHFile: already stripped?")
            return
        }

        dotHFile.writeText(dotHRewritten)
        logger.info("Stripped swift_name lines from $dotHFile")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink> {
        doLast {
            val dotHFile = File(outputFile.get(), "Headers/MEGAAnalyticsiOS.h")
            if (dotHFile.exists()){
                stripLines(dotHFile)
            }
        }
    }
}