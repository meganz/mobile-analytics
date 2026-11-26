package src.main.kotlin

import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject

/**
 * Compares the event ID JSON files with the tip of [baselineRef] and fails if an existing event's
 * ID would change. IDs are stored with every analytics record, so changing one corrupts data.
 *
 * - An existing event whose ID changes always fails.
 * - Duplicate IDs within a file always fail.
 * - A new event that takes an ID from the baseline always fails.
 * - An ID outside [GENERATED_ID_RANGE] fails, except in the legacy file, which keeps the IDs of
 *   the events that existed before this library.
 * - A new ID entry fails if no event of that type is declared in [manifestFile], because then
 *   the processor did not write it.
 * - A removed event fails unless [allowRemovals] is set, for deliberate clean-ups of unused events.
 *
 * The baseline is the tip of [baselineRef], not the merge base. Two branches that each add an
 * event from the same merge base get the same next free ID. Against the merge base both pass, and
 * the second merge reuses an ID. Against the tip, a branch that is behind the baseline fails until
 * it is rebased and its IDs are generated again.
 */
abstract class VerifyEventIdStabilityTask : DefaultTask() {

    @get:Internal
    abstract val resourceDir: DirectoryProperty

    /** Manifest of HEAD, written by the event processor. */
    @get:Internal
    abstract val manifestFile: RegularFileProperty

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
        val base = git(dir, "rev-parse", "--verify", "${baselineRef.get()}^{commit}")
            ?: throw GradleException(
                "Cannot resolve '${baselineRef.get()}'. Fetch it first, e.g. `git fetch origin main`."
            )
        val declared = parseEventManifest(manifestFile.get().asFile.readText()).keys

        val baselineFiles = git(dir, "ls-tree", "--name-only", base, "--", ".")
            .orEmpty().lines().filter { it.endsWith(".json") }
        val currentFiles = dir.listFiles { file -> file.extension == "json" }.orEmpty()
            .map(File::getName)

        val errors = mutableListOf<String>()
        val removals = mutableListOf<String>()

        (baselineFiles + currentFiles).distinct().sorted().forEach { fileName ->
            val type = fileName.removeSuffix(".json")
            val result = checkEventIds(
                fileName = fileName,
                before = git(dir, "show", "$base:./$fileName")?.let(::parseIds).orEmpty(),
                after = File(dir, fileName).takeIf { it.exists() }?.let { parseIds(it.readText()) }.orEmpty(),
                declaredNames = declared.filter { it.type == type }.map { it.name }.toSet(),
            )
            errors += result.errors
            removals += result.removals
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
                    if (git(dir, "merge-base", "--is-ancestor", base, "HEAD") == null) {
                        "\nHEAD is not based on the tip of ${baselineRef.get()}. Rebase onto it first."
                    } else {
                        ""
                    } +
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

/** IDs that the processor assigns to events of every type except legacy events. */
val GENERATED_ID_RANGE = 0..999

/**
 * Result of [checkEventIds]
 *
 * @property errors problems that always fail the check
 * @property removals removed events, which fail unless removals are allowed
 */
data class EventIdCheck(val errors: List<String>, val removals: List<String>)

/**
 * Checks the IDs of one identifier JSON file against the same file in the baseline
 *
 * @param fileName name of the JSON file, which is the event type followed by `.json`
 * @param before IDs by event name in the baseline
 * @param after IDs by event name in HEAD
 * @param declaredNames names of the events of this type declared in HEAD
 */
fun checkEventIds(
    fileName: String,
    before: Map<String, Int>,
    after: Map<String, Int>,
    declaredNames: Set<String>,
): EventIdCheck {
    val errors = mutableListOf<String>()
    val removals = mutableListOf<String>()

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
        if (name !in declaredNames) {
            errors += "$fileName: new event $name has no declaration"
        }
    }

    if (fileName != LEGACY_ID_FILE) {
        after.filterValues { it !in GENERATED_ID_RANGE }.forEach { (name, id) ->
            errors += "$fileName: $name has ID $id, outside $GENERATED_ID_RANGE"
        }
    }

    after.entries.groupBy({ it.value }, { it.key }).filterValues { it.size > 1 }
        .forEach { (id, names) -> errors += "$fileName: ID $id is shared by ${names.joinToString()}" }

    return EventIdCheck(errors, removals)
}

private const val LEGACY_ID_FILE = "LegacyEvent.json"
