package mega.privacy.mobile.analytics.processor.generator

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.processing.CodeGenerator
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.SourceFile.Companion.kotlin
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.symbolProcessorProviders
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import mega.privacy.mobile.analytics.processor.TestProcessorProvider
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.io.File

internal class EventCodeGeneratorTest {
    private lateinit var underTest: EventCodeGenerator

    @TempDir
    lateinit var temporaryFolder: File

    private val codeGenerator = mock<CodeGenerator>()
    private val idProvider = mock<IdProvider>()
    private val idGenerator = mock<IdGenerator>()

    @BeforeEach
    internal fun setUp() {
        underTest = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = mock {
                on { invoke(any()) }.thenReturn(
                    GeneralEventVisitor(
                        idGenerator,
                        ConstructorParameterMapper()
                    )
                )
            },
            annotationClass = GeneralEvent::class
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
            ?.find { it.name == "${GeneralEvent::class.simpleName}.json" }

        assertThat(idFile).isNotNull()
    }

    @Test
    internal fun `test that code file is created`() {
        val compilation = prepareCompilation(input)
        compilation.compile()

        val sourceFile = compilation.kspSourcesDir
            .walkTopDown()
            .filter { it.isFile }
            .find { it.name == "GeneralEvents.kt" }

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


    private val input = kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.StaticValue

@GeneralEvent
interface Test1

@GeneralEvent
interface Test2

@GeneralEvent
class TestTab3(
    val foo: Int,
    @StaticValue("my value")
    val bar: String,
)

    """
    )

    private val output =
        """
    package mega.privacy.mobile.analytics.event
    
    import kotlin.Any
    import kotlin.Int
    import kotlin.String
    import kotlin.collections.Map
    import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
    
    public object Test1Event : GeneralEventIdentifier {
      override val eventName: String = "Test1"
    
      override val uniqueIdentifier: Int = 0
    
      override val info: Map<String, Any?> = emptyMap()
    
    }
    
    public object Test2Event : GeneralEventIdentifier {
      override val eventName: String = "Test2"
    
      override val uniqueIdentifier: Int = 1
    
      override val info: Map<String, Any?> = emptyMap()
    
    }
    
    public class TestTab3Event(
      foo: Int,
    ) : GeneralEventIdentifier {
      override val eventName: String = "TestTab3"
    
      override val uniqueIdentifier: Int = 2
    
      override val info: Map<String, Any?> = mapOf(
      "foo" to foo,
      "bar" to "my value",
      )
    }

    """.trimIndent()
}



