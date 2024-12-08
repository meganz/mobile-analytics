package mega.privacy.mobile.analytics.processor.generator

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.processing.CodeGenerator
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.kspIncremental
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.symbolProcessorProviders
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import mega.privacy.mobile.analytics.processor.TestProcessorProvider
import mega.privacy.mobile.analytics.processor.generator.parameter.GeneratorCodeTestParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateIdRequest
import mega.privacy.mobile.analytics.processor.visitor.AnalyticsEventVisitor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.io.File
import kotlin.reflect.KClass

abstract class EventCodeGeneratorTest<T : GenerateIdRequest> {
    private lateinit var underTest: EventCodeGenerator

    protected abstract val testParams: GeneratorCodeTestParameter
    protected abstract val annotationClass: KClass<*>
    protected abstract fun visitor(idGenerator: IdGenerator<T>): AnalyticsEventVisitor<T>

    @TempDir
    lateinit var temporaryFolder: File
    private val codeGenerator = mock<CodeGenerator>()
    private val idProvider = mock<IdProvider>()
    private val idGenerator = mock<IdGenerator<T>>()


    @BeforeEach
    internal fun setUp() {
        underTest = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = mock {
                on { invoke(any()) }.thenReturn(
                    visitor(idGenerator)
                )
            },
            annotationClass = annotationClass
        )
    }

    @Test
    internal fun `test that compilation is successful`() {
        val result = compile(testParams.sourceFile)
        assertThat(result.exitCode).isEqualTo(KotlinCompilation.ExitCode.OK)
    }

    @Test
    internal fun `test that identifier file is created`() {
        val compilation = prepareCompilation(testParams.sourceFile)
        compilation.compile()

        val idFile = temporaryFolder.listFiles()
            ?.find { it.name == "${annotationClass.simpleName}.json" }

        assertThat(idFile).isNotNull()
    }

    @Test
    internal fun `test that code file is created`() {
        val compilation = prepareCompilation(testParams.sourceFile)
        compilation.compile()

        val sourceFile = compilation.kspSourcesDir
            .walkTopDown()
            .filter { it.isFile }
            .find { it.name == testParams.outPutFileName }

        assertThat(sourceFile).isNotNull()
        val fileContent = sourceFile?.readText()
        assertThat(fileContent).contains(testParams.expectedOutput)
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