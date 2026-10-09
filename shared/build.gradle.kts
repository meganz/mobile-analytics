import groovy.util.Node
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import src.main.kotlin.HtmlTableTask
import src.main.kotlin.VerifyEventIdStabilityTask
import src.main.kotlin.VerifySwiftPackageEventsTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.multiplatform.swift)
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
        namespace = "mega.privacy.mobile.analytics"
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

    val exportedModules = listOf(":analytics-annotations", ":analytics-core")
        .map { path -> project.dependencies.project(path) }
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = false
            exportedModules.forEach { export(it) }

            transitiveExport = true
        }
    }

    sourceSets {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain")
            dependencies {
                api(project(":analytics-annotations"))
                api(project(":analytics-core"))

                implementation(libs.kotlinx.coroutines)
            }
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

// Event classes are generated once into commonMain metadata; every other compilation (and the
// sources jars) must wait for that generation to finish.
tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
tasks.matching { it.name.endsWith("sourcesJar", ignoreCase = true) }.configureEach {
    dependsOn("kspCommonMainKotlinMetadata")
}
tasks.register("sourceJar") {
    dependsOn("kspCommonMainKotlinMetadata")
}

// AGP registers the Android bundle/sources tasks after evaluation, so the publication must be
// declared once they exist.
afterEvaluate {
    publishing.publications {
        val libVersion = rootProject.extra.get("androidLibVersion") as String
        create<MavenPublication>("aar") {
            groupId = "mega.privacy.mobile"
            artifactId = "analytics-events-android"
            version = libVersion
            artifact(tasks.named("bundleAndroidMainAar"))
            artifact(tasks.named("androidSourcesJar")) {
                classifier = "sources"
                extension = "jar"
            }

            pom.withXml {
                addDependencyInPOM(libVersion)
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", project(":analytics-processor"))
}

ksp {
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
    Node(kotlinxSerializationJsonDep, "version").apply { setValue(libs.versions.kotlinx.serialization.get()) }
    Node(kotlinxSerializationJsonDep, "scope").apply { setValue("compile") }
}

tasks.named("artifactoryPublish") {
    dependsOn("assemble")
    dependsOn("androidSourcesJar")
}


tasks.register<HtmlTableTask>("generateHtmlTables")

tasks.register<VerifySwiftPackageEventsTask>("verifySwiftPackageEvents") {
    xcFramework.set(rootProject.layout.projectDirectory.dir("SwiftPackages/MEGAAnalyticsiOS/MEGAAnalyticsiOS.xcframework"))
    eventSourceDir.set(layout.projectDirectory.dir("src/commonMain/kotlin/mega/privacy/mobile/analytics/event"))
    resourceDir.set(layout.projectDirectory.dir("src/commonMain/resources"))
    mustRunAfter("createSwiftPackage")
}

tasks.register<VerifyEventIdStabilityTask>("verifyEventIdStability") {
    resourceDir.set(layout.projectDirectory.dir("src/commonMain/resources"))
    baselineRef.set(providers.gradleProperty("eventIdBaseline").orElse("origin/main"))
    allowRemovals.set(providers.gradleProperty("allowEventIdRemoval").map(String::toBoolean).orElse(false))
    // KSP rewrites the JSON files, so check what it produced.
    mustRunAfter("kspCommonMainKotlinMetadata")
}

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
