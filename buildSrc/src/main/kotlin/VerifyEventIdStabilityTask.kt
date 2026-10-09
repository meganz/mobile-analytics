package src.main.kotlin

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject

/**
 * Compares the event ID JSON files with the merge base of [baselineRef] and fails if an existing
 * event's ID would change. IDs are stored with every analytics record, so changing one corrupts data.
 *
 * - An existing event whose ID changes always fails.
 * - Duplicate IDs within a file always fail.
 * - A new event that takes an ID from the baseline always fails.
 * - A removed event fails unless [allowRemovals] is set, for deliberate clean-ups of unused events.
 */
abstract class VerifyEventIdStabilityTask : DefaultTask() {

    @get:Internal
    abstract val resourceDir: DirectoryProperty

    @get:Input
    abstract val baselineRef: Property<String>

    @get:Input
    abstract val allowRemovals: Property<Boolean>

    @get:Inject
    abstract val execOperations: ExecOperations

    init {
        group = "verification"
        description = "Verifies that existing analytics event IDs have not changed"
        baselineRef.convention("origin/main")
        allowRemovals.convention(false)
        // The result depends on git history, so never treat it as up to date.
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun verify() {
        val dir = resourceDir.get().asFile
        val base = git(dir, "merge-base", "HEAD", baselineRef.get())
            ?: throw GradleException(
                "Cannot find a merge base with '${baselineRef.get()}'. " +
                    "Fetch it first, e.g. `git fetch origin main`."
            )

        val baselineFiles = git(dir, "ls-tree", "--name-only", base, "--", ".")
            .orEmpty().lines().filter { it.endsWith(".json") }
        val currentFiles = dir.listFiles { file -> file.extension == "json" }.orEmpty()
            .map(File::getName)

        val errors = mutableListOf<String>()
        val removals = mutableListOf<String>()

        (baselineFiles + currentFiles).distinct().sorted().forEach { fileName ->
            val before = git(dir, "show", "$base:./$fileName")?.let(::parseIds).orEmpty()
            val after = File(dir, fileName).takeIf { it.exists() }?.let { parseIds(it.readText()) }.orEmpty()

            before.forEach { (name, id) ->
                when (val newId = after[name]) {
                    null -> removals += "$fileName: $name ($id) was removed"
                    id -> Unit
                    else -> errors += "$fileName: $name changed from $id to $newId"
                }
            }

            val namesById = before.entries.associate { (name, id) -> id to name }
            after.filterKeys { it !in before }.forEach { (name, id) ->
                namesById[id]?.let { previous ->
                    errors += "$fileName: new event $name reuses ID $id from $previous"
                }
            }

            after.entries.groupBy({ it.value }, { it.key }).filterValues { it.size > 1 }
                .forEach { (id, names) -> errors += "$fileName: ID $id is shared by ${names.joinToString()}" }
        }

        if (!allowRemovals.get()) {
            errors += removals
        } else if (removals.isNotEmpty()) {
            logger.warn("Event removals allowed:\n" + removals.joinToString("\n"))
        }

        if (errors.isNotEmpty()) {
            throw GradleException(
                "Event IDs are not stable against ${baselineRef.get()} ($base):\n" +
                    errors.joinToString("\n") +
                    if (removals.isNotEmpty() && !allowRemovals.get()) {
                        "\nTo remove unused events on purpose, run with -PallowEventIdRemoval=true " +
                            "(CI: put [allow-event-id-removal] in a commit message)."
                    } else {
                        ""
                    }
            )
        }
        logger.lifecycle("Event IDs are stable against ${baselineRef.get()} ($base)")
    }

    private fun parseIds(json: String): Map<String, Int> =
        (JsonSlurper().parseText(json) as Map<*, *>).entries
            .associate { (name, id) -> name.toString() to (id as Number).toInt() }

    /** Runs git in [dir] and returns its trimmed output, or null if git fails. */
    private fun git(dir: File, vararg args: String): String? {
        val output = ByteArrayOutputStream()
        val result = execOperations.exec {
            workingDir = dir
            commandLine = listOf("git") + args
            standardOutput = output
            errorOutput = ByteArrayOutputStream()
            isIgnoreExitValue = true
        }
        return if (result.exitValue == 0) output.toString().trim() else null
    }
}
