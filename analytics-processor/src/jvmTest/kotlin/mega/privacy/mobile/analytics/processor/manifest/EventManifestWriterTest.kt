package mega.privacy.mobile.analytics.processor.manifest

import com.google.common.truth.Truth.assertThat
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.configureKsp
import kotlinx.serialization.json.Json
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import mega.privacy.mobile.analytics.processor.TestProcessorProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class EventManifestWriterTest {

    @TempDir
    lateinit var temporaryFolder: File

    private val manifestFile by lazy { File(temporaryFolder, "out/eventManifest.json") }

    @Test
    fun `test that manifest lists every event ordered by type and name`() {
        val manifest = compile()

        assertThat(manifest.events.map { "${it.type}/${it.name}" }).containsExactly(
            "ButtonPressEvent/AButton",
            "ButtonPressEvent/BButton",
            "GeneralEvent/Opened",
            "LegacyEvent/OldEvent",
            "MenuItemEvent/Overflow",
        ).inOrder()
    }

    @Test
    fun `test that manifest records annotation arguments without blank values`() {
        val event = compile().event("BButton")

        assertThat(event.annotationArguments).containsExactly(
            "buttonName", "b",
            "screen", "Home",
        )
        assertThat(event.legacyId).isNull()
    }

    @Test
    fun `test that manifest records enum annotation arguments by entry name`() {
        val event = compile().event("Overflow")

        assertThat(event.annotationArguments["menuType"]).isEqualTo("Toolbar")
    }

    @Test
    fun `test that manifest records parameters and static values separately`() {
        val event = compile().event("Opened")

        assertThat(event.parameters).containsExactly(
            ManifestParameter("count", "kotlin.Int?", null),
            ManifestParameter("source", "Opened.Source", listOf("Link", "Folder")),
        ).inOrder()
        assertThat(event.staticValues).containsExactly("version", "2")
    }

    @Test
    fun `test that manifest records the legacy id when the event is a legacy event`() {
        val event = compile().event("OldEvent")

        assertThat(event.legacyId).isEqualTo(12)
        assertThat(event.id).isEqualTo(12)
    }

    @Test
    fun `test that manifest is not written when manifest path is not set`() {
        compile(withManifest = false)

        assertThat(manifestFile.exists()).isFalse()
    }

    private fun EventManifest.event(name: String) = events.single { it.name == name }

    private fun compile(withManifest: Boolean = true): EventManifest {
        val options = buildMap {
            put(AnalyticsEventProcessor.RESOURCE_PATH_KEY, temporaryFolder.path)
            if (withManifest) put(AnalyticsEventProcessor.MANIFEST_PATH_KEY, manifestFile.path)
        }
        val result = KotlinCompilation().apply {
            sources = listOf(source)
            workingDir = temporaryFolder
            inheritClassPath = true
            configureKsp {
                symbolProcessorProviders += TestProcessorProvider(options)
            }
            verbose = false
        }.compile()
        assertThat(result.exitCode).isEqualTo(KotlinCompilation.ExitCode.OK)
        return if (withManifest) {
            Json.decodeFromString(EventManifest.serializer(), manifestFile.readText())
        } else {
            EventManifest(emptyList())
        }
    }

    private val source = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.StaticValue

@ButtonPressEvent(buttonName = "b", screen = "Home")
interface BButton

@ButtonPressEvent(buttonName = "a")
interface AButton

@MenuItemEvent(menuItem = "Overflow", menuType = MenuItemEvent.MenuType.Toolbar)
interface Overflow

@LegacyEvent(eventId = 12)
interface OldEvent

@GeneralEvent
class Opened(
    val count: Int?,
    val source: Source,
    @StaticValue("2")
    val version: String,
) {
    enum class Source { Link, Folder }
}
        """
    )
}
