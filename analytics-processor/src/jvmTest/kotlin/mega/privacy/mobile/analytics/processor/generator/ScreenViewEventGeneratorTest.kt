package mega.privacy.mobile.analytics.processor.generator

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.SourceFile.Companion.kotlin
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.symbolProcessorProviders
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.mockito.kotlin.mock
import java.io.File

internal class ScreenViewEventGeneratorTest {
    private lateinit var underTest: ScreenViewEventGenerator

    @TempDir
    lateinit var temporaryFolder: File

    private val codeGenerator = mock<CodeGenerator>()
    private val idProvider = mock<IdProvider>()
    private val idGenerator = mock<IdGenerator>()

    @BeforeEach
    internal fun setUp() {
        underTest = ScreenViewEventGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            idGenerator = idGenerator,
        )
    }

    @Test
    internal fun `test that compilation is successful`() {
        val result = compile(input)
        assertThat(result.exitCode).isEqualTo(KotlinCompilation.ExitCode.OK)
    }

    @Test
    internal fun `test that identifier file is created`() {
        val compilation = prepareCompilation(input)
        compilation.compile()

        val idFile = temporaryFolder.listFiles()
            ?.find { it.name == "${ScreenViewEvent::class.simpleName}.json" }

        assertThat(idFile).isNotNull()
    }

    @Test
    internal fun `test that code file is created`() {
        val compilation = prepareCompilation(input)
        compilation.compile()

        val sourceFile = compilation.kspSourcesDir
            .walkTopDown()
            .filter { it.isFile }
            .find { it.name == "ScreenViewEvents.kt" }

        assertThat(sourceFile).isNotNull()
        val fileContent = sourceFile?.readText()
        assertThat(fileContent).contains(output)
    }

    private fun prepareCompilation(vararg sourceFiles: SourceFile): KotlinCompilation {
        return KotlinCompilation()
            .apply {
                sources = sourceFiles.asList()
                workingDir = temporaryFolder
                inheritClassPath = true
                symbolProcessorProviders =
                    listOf(
                        TestProcessorProvider(
                            options = mapOf(
                                AnalyticsEventProcessor.resourcePathKey to temporaryFolder.path
                            )
                        )
                    )
                verbose = false
                kspIncremental = true // The default now
                multiplatform = true
            }
    }

    private fun compile(vararg sourceFiles: SourceFile): KotlinCompilation.Result {
        return prepareCompilation(*sourceFiles).compile()
    }
}

class TestProcessorProvider(private val options: Map<String, String>) : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return AnalyticsEventProcessor(
            environment.codeGenerator,
            environment.logger,
            options
        )
    }
}

val input = kotlin(
    "input.kt",
    """
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent

@ScreenViewEvent
interface TestScreen1

@ScreenViewEvent
interface TestScreen2

@ScreenViewEvent
interface TestScreen3

    """
)

val output =
    """
        package mega.privacy.mobile.analytics.event

        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier

        public object TestScreen1Event : ScreenViewEventIdentifier {
          override val eventName: String = "TestScreen1"

          override val uniqueIdentifier: Int = 0
        }

        public object TestScreen2Event : ScreenViewEventIdentifier {
          override val eventName: String = "TestScreen2"

          override val uniqueIdentifier: Int = 1
        }

        public object TestScreen3Event : ScreenViewEventIdentifier {
          override val eventName: String = "TestScreen3"

          override val uniqueIdentifier: Int = 2
        }

    """.trimIndent()