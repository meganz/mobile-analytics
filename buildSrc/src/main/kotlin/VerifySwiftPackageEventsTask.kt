package src.main.kotlin

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

/**
 * Fails when an event declared in the event sources is missing from any slice of the generated
 * XCFramework. Guards against the KSP output being dropped before the native link, which once
 * shipped an iOS framework without any events.
 *
 * Expected events are the names that are both declared in [eventSourceDir] and listed in the
 * JSON files in [resourceDir]. The JSON files also keep retired names, so they cannot be used alone.
 */
abstract class VerifySwiftPackageEventsTask : DefaultTask() {

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val xcFramework: DirectoryProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val eventSourceDir: DirectoryProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val resourceDir: DirectoryProperty

    init {
        group = "verification"
        description = "Verifies that every declared event is exported by the generated XCFramework"
    }

    @TaskAction
    fun verify() {
        val expected = expectedEventClassNames()
        if (expected.isEmpty()) {
            throw GradleException("No events found in ${eventSourceDir.get().asFile}")
        }

        val headers = xcFramework.get().asFileTree.matching { include("**/Headers/*.h") }.files
        if (headers.isEmpty()) {
            throw GradleException("No framework headers found in ${xcFramework.get().asFile}")
        }

        val failures = headers.sorted().mapNotNull { header ->
            val exported = SWIFT_NAME.findAll(header.readText()).map { it.groupValues[1] }.toSet()
            val missing = expected - exported
            if (missing.isEmpty()) {
                null
            } else {
                val slice = header.relativeTo(xcFramework.get().asFile).invariantSeparatorsPath
                "$slice is missing ${missing.size} of ${expected.size} events, e.g. " +
                    missing.sorted().take(5).joinToString()
            }
        }

        if (failures.isNotEmpty()) {
            throw GradleException(
                "The XCFramework does not export all events:\n" + failures.joinToString("\n")
            )
        }
        logger.lifecycle("All ${expected.size} events are exported by ${headers.size} framework slices")
    }

    private fun expectedEventClassNames(): Set<String> {
        val declared = eventSourceDir.get().asFile.listFiles { file -> file.extension == "kt" }
            .orEmpty()
            .map { it.readText() }
            .filterNot { it.contains("@file:Exclude") }
            .flatMap { source -> DECLARATION.findAll(source).map { it.groupValues[1] } }
            .toSet()

        val listed = resourceDir.get().asFile.listFiles { file -> file.extension == "json" }
            .orEmpty()
            .flatMap { (JsonSlurper().parse(it) as Map<*, *>).keys.map(Any?::toString) }

        return listed.filter { it in declared }.map { "${it}Event" }.toSet()
    }

    private companion object {
        val DECLARATION = Regex("""\b(?:interface|class)\s+(\w+)""")
        val SWIFT_NAME = Regex("""swift_name\("(\w+)"\)""")
    }
}
