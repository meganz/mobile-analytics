package src.main.kotlin

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files
import javax.inject.Inject

/**
 * Compares the event manifest of HEAD with the event manifest of the tip of [baselineRef] and fails
 * if an existing event was removed or modified. Consumers call the generated classes and dashboards
 * read the payload, so a change to an existing event breaks them even when its ID stays the same.
 *
 * The baseline manifest is generated with the processor of HEAD from the event sources of the
 * baseline, in a temporary git worktree. Then a difference in the manifest comes only from the event
 * declarations, and a baseline from before the manifest existed can also be used.
 *
 * The task always writes [reportFile]. It fails on a removal or a modification unless
 * [allowChanges] is set or a commit message in `<baseline>..HEAD` contains [ALLOW_EVENT_API_CHANGE].
 */
abstract class VerifyEventApiStabilityTask : DefaultTask() {

    /** Root of the git repository. */
    @get:Internal
    abstract val repositoryDir: DirectoryProperty

    /** Source directory that holds the event declarations and the identifier JSON files. */
    @get:Internal
    abstract val eventSourceDir: DirectoryProperty

    /** Manifest of HEAD, written by the event processor. */
    @get:Internal
    abstract val manifestFile: RegularFileProperty

    /** Gradle task path that runs the event processor and writes [manifestFile]. */
    @get:Input
    abstract val generateTaskPath: Property<String>

    @get:Input
    abstract val baselineRef: Property<String>

    @get:Input
    abstract val allowChanges: Property<Boolean>

    @get:OutputFile
    abstract val reportFile: RegularFileProperty

    @get:Inject
    abstract val execOperations: ExecOperations

    init {
        group = "verification"
        description = "Verifies that existing analytics events keep their annotation, parameters and payload"
        baselineRef.convention("origin/main")
        allowChanges.convention(false)
        // The result depends on git history, so never treat it as up to date.
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun verify() {
        val repo = repositoryDir.get().asFile
        val base = git(repo, "rev-parse", "--verify", "${baselineRef.get()}^{commit}")
            ?: throw GradleException(
                "Cannot resolve '${baselineRef.get()}'. Fetch it first, e.g. `git fetch origin main`."
            )
        val current = parseEventManifest(manifestFile.get().asFile.readText())
        val baseline = if (eventSourcesChangedSince(repo, base)) {
            parseEventManifest(generateBaselineManifest(repo, base))
        } else {
            current
        }

        val diff = compareEventManifests(baseline, current)
        val allowed = allowChanges.get() ||
            isEventApiChangeAllowed(git(repo, "log", "$base..HEAD", "--format=%B").orEmpty())
        writeReport(diff, base, allowed)

        if (diff.added.isNotEmpty()) {
            logger.lifecycle("Added events:\n" + diff.added.joinToString("\n") { "  $it" })
        }
        if (!diff.hasBreakingChanges) {
            logger.lifecycle("Event API is stable against ${baselineRef.get()} ($base)")
            return
        }
        val message = describeBreakingChanges(diff)
        if (allowed) {
            logger.warn("Event API changes allowed:\n$message")
            return
        }
        throw GradleException(
            "Event API is not stable against ${baselineRef.get()} ($base):\n$message\n" +
                staleBaselineHint(repo, base) +
                "To change or remove existing events on purpose, put $ALLOW_EVENT_API_CHANGE in a " +
                "commit message (locally: -PallowEventApiChange=true). Report: ${reportFile.get().asFile}"
        )
    }

    private fun eventSourcesChangedSince(repo: File, base: String): Boolean {
        val sources = eventSourceDir.get().asFile.relativeTo(repo).path
        return git(repo, "diff", "--quiet", base, "--", sources) == null ||
            !git(repo, "ls-files", "--others", "--exclude-standard", "--", sources).isNullOrEmpty()
    }

    private fun generateBaselineManifest(repo: File, base: String): String {
        val worktree = Files.createTempDirectory("event-api-baseline").toFile()
        val sources = eventSourceDir.get().asFile.relativeTo(repo).path
        try {
            gitOrFail(repo, "worktree", "add", "--detach", worktree.path, "HEAD")
            File(worktree, sources).deleteRecursively()
            gitOrFail(worktree, "checkout", base, "--", sources)
            File(repo, "local.properties").takeIf { it.exists() }
                ?.copyTo(File(worktree, "local.properties"))

            val output = ByteArrayOutputStream()
            val result = execOperations.exec {
                workingDir = worktree
                commandLine = listOf("./gradlew", "--console=plain", "-q", generateTaskPath.get())
                standardOutput = output
                errorOutput = output
                isIgnoreExitValue = true
            }
            if (result.exitValue != 0) {
                throw GradleException(
                    "Cannot generate the event manifest of $base:\n" + output.toString().takeLast(4_000)
                )
            }
            val manifest = File(worktree, manifestFile.get().asFile.relativeTo(repo).path)
            return manifest.readText()
        } finally {
            git(repo, "worktree", "remove", "--force", worktree.path)
            worktree.deleteRecursively()
        }
    }

    private fun staleBaselineHint(repo: File, base: String): String =
        if (git(repo, "merge-base", "--is-ancestor", base, "HEAD") == null) {
            "HEAD is not based on the tip of ${baselineRef.get()}, so its newer events show as removed. " +
                "Rebase onto ${baselineRef.get()} first.\n"
        } else {
            ""
        }

    private fun writeReport(diff: EventApiDiff, base: String, allowed: Boolean) {
        val report = mapOf(
            "baselineRef" to baselineRef.get(),
            "baselineCommit" to base,
            "allowed" to allowed,
            "added" to diff.added.map { it.toMap() },
            "removed" to diff.removed.map { it.toMap() },
            "modified" to diff.modified.map { it.key.toMap() + ("reasons" to it.reasons) },
        )
        reportFile.get().asFile.apply {
            parentFile.mkdirs()
            writeText(JsonOutput.prettyPrint(JsonOutput.toJson(report)) + "\n")
        }
    }

    private fun gitOrFail(dir: File, vararg args: String): String =
        git(dir, *args) ?: throw GradleException("git ${args.joinToString(" ")} failed in $dir")

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

/** Commit message marker that allows existing events to be changed or removed. */
const val ALLOW_EVENT_API_CHANGE = "[allow-event-api-change]"

/**
 * Returns true when [commitMessages] of the branch contain [ALLOW_EVENT_API_CHANGE]
 */
fun isEventApiChangeAllowed(commitMessages: String): Boolean =
    commitMessages.contains(ALLOW_EVENT_API_CHANGE)

/**
 * Identifies an event. The same name can be used by events of different types.
 *
 * @property type simple name of the event annotation
 * @property name event name
 */
data class EventKey(val type: String, val name: String) : Comparable<EventKey> {
    override fun compareTo(other: EventKey) = compareValuesBy(this, other, { it.type }, { it.name })
    override fun toString() = "$type $name"
    internal fun toMap() = mapOf("type" to type, "name" to name)
}

/**
 * An existing event whose API changed
 *
 * @property key the event
 * @property reasons one entry for each field that changed
 */
data class ModifiedEvent(val key: EventKey, val reasons: List<String>)

/**
 * Difference between two event manifests
 *
 * @property added events only in the current manifest
 * @property removed events only in the baseline manifest
 * @property modified events in both manifests whose API changed
 */
data class EventApiDiff(
    val added: List<EventKey>,
    val removed: List<EventKey>,
    val modified: List<ModifiedEvent>,
) {
    /** True when an existing event was removed or modified. */
    val hasBreakingChanges get() = removed.isNotEmpty() || modified.isNotEmpty()
}

/**
 * Parses an event manifest written by the event processor
 *
 * @return the fields of each event by its key
 */
fun parseEventManifest(json: String): Map<EventKey, Map<String, Any?>> {
    val events = (JsonSlurper().parseText(json) as Map<*, *>)["events"] as List<*>
    return events.associate { event ->
        val fields = (event as Map<*, *>).entries.associate { (key, value) -> key.toString() to value }
        EventKey(fields["type"].toString(), fields["name"].toString()) to fields
    }
}

/**
 * Compares two parsed event manifests
 *
 * @param baseline manifest of the baseline commit
 * @param current manifest of HEAD
 */
fun compareEventManifests(
    baseline: Map<EventKey, Map<String, Any?>>,
    current: Map<EventKey, Map<String, Any?>>,
): EventApiDiff = EventApiDiff(
    added = (current.keys - baseline.keys).sorted(),
    removed = (baseline.keys - current.keys).sorted(),
    modified = baseline.keys.intersect(current.keys).sorted().mapNotNull { key ->
        eventChanges(baseline.getValue(key), current.getValue(key))
            .takeIf { it.isNotEmpty() }
            ?.let { ModifiedEvent(key, it) }
    },
)

/**
 * Lists the removed and modified events, one line for each reason
 */
fun describeBreakingChanges(diff: EventApiDiff): String =
    (diff.removed.map { "$it was removed" } +
        diff.modified.flatMap { event -> event.reasons.map { "${event.key}: $it" } })
        .joinToString("\n")

private fun eventChanges(before: Map<String, Any?>, after: Map<String, Any?>): List<String> =
    (before.keys + after.keys).distinct().sorted().flatMap { field ->
        when (field) {
            "name", "type" -> emptyList()
            "annotationArguments" -> mapChanges("annotation argument", before[field], after[field])
            "staticValues" -> mapChanges("static value", before[field], after[field])
            "parameters" -> parameterChanges(before[field], after[field])
            else -> if (before[field] != after[field]) {
                listOf("$field changed from ${before[field]} to ${after[field]}")
            } else {
                emptyList()
            }
        }
    }

private fun mapChanges(label: String, before: Any?, after: Any?): List<String> {
    val old = before as? Map<*, *> ?: emptyMap<Any, Any>()
    val new = after as? Map<*, *> ?: emptyMap<Any, Any>()
    return (old.keys + new.keys).map { it.toString() }.distinct().sorted().mapNotNull { key ->
        when {
            key !in old -> "$label '$key' was added with value '${new[key]}'"
            key !in new -> "$label '$key' was removed"
            old[key] != new[key] -> "$label '$key' changed from '${old[key]}' to '${new[key]}'"
            else -> null
        }
    }
}

private fun parameterChanges(before: Any?, after: Any?): List<String> {
    val old = (before as? List<*>).orEmpty().map { it as Map<*, *> }
    val new = (after as? List<*>).orEmpty().map { it as Map<*, *> }
    val oldByName = old.associateBy { it["name"].toString() }
    val newByName = new.associateBy { it["name"].toString() }
    val changes = mutableListOf<String>()
    oldByName.keys.filterNot { it in newByName }.forEach { changes += "parameter '$it' was removed" }
    newByName.forEach { (name, parameter) ->
        val previous = oldByName[name]
        when {
            previous == null -> changes += "parameter '$name' was added with type ${parameter["type"]}"
            previous["type"] != parameter["type"] ->
                changes += "parameter '$name' type changed from ${previous["type"]} to ${parameter["type"]}"

            previous["enumValues"] != parameter["enumValues"] ->
                changes += "parameter '$name' enum values changed from ${previous["enumValues"]} to ${parameter["enumValues"]}"
        }
    }
    val commonOld = old.map { it["name"].toString() }.filter { it in newByName }
    val commonNew = new.map { it["name"].toString() }.filter { it in oldByName }
    if (commonOld != commonNew) {
        changes += "parameter order changed from $commonOld to $commonNew"
    }
    return changes
}
