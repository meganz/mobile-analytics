package mega.privacy.mobile.analytics.processor.generator

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.processing.CodeGenerator
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.symbolProcessorProviders
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import mega.privacy.mobile.analytics.processor.TestProcessorProvider
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.io.File

class ExcludedGeneratorTest {
    private lateinit var underTest: EventCodeGenerator
    private val annotationClass = TabSelectedEvent::class
    private val codeGenerator = mock<CodeGenerator>()
    private val idProvider = mock<IdProvider>()
    private val idGenerator = mock<SingleRangeIdGenerator>()

    private val visitor = TabSelectedVisitor(idGenerator)

    @TempDir
    lateinit var temporaryFolder: File

    @BeforeEach
    internal fun setUp() {
        underTest = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = mock {
                on { invoke(any()) }.thenReturn(
                    visitor
                )
            },
            annotationClass = annotationClass
        )
    }

    @Test
    internal fun `test that ignored file produces no results`() {
        val compilation = prepareCompilation(excludedFile)
        compilation.compile()

        val sourceFile = compilation.kspSourcesDir
            .walkTopDown()
            .filter { it.isFile }
            .find { it.name == "TabSelectedEvents.kt" }

        assertThat(sourceFile).isNull()
    }

    @Test
    internal fun `test that ignored class produces no results`() {
        val compilation = prepareCompilation(excludedClass)
        compilation.compile()

        val sourceFile = compilation.kspSourcesDir
            .walkTopDown()
            .filter { it.isFile }
            .find { it.name == "TabSelectedEvents.kt" }

        assertThat(sourceFile).isNull()
    }

    private val excludedFile = SourceFile.kotlin(
        "input.kt",
        """
@file:Exclude
import mega.privacy.mobile.analytics.annotations.Exclude
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent

@TabSelectedEvent(tabName = "tab1", screenName = "screen1")
interface TestTab1
    """
    )

    private val excludedClass = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.Exclude
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent

@Exclude
@TabSelectedEvent(tabName = "tab1", screenName = "screen1")
interface TestTab1
    """
    )

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
                                AnalyticsEventProcessor.RESOURCE_PATH_KEY to temporaryFolder.path
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