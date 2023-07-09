package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.*
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import mega.privacy.mobile.analytics.annotations.StaticValue
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import kotlin.reflect.KClass

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ItemSelectedEventVisitorTest : AnalyticsVisitorTest<ItemSelectedEventVisitor>() {
    private val constructorParameterMapper = mock<ConstructorParameterMapper>()
    override fun initialiseUnderTest(idGenerator: IdGenerator) = ItemSelectedEventVisitor(
        constructorParameterMapper = constructorParameterMapper,
        idGenerator = idGenerator,
    )

    @Test
    internal fun `test that event extends correct class`() {
        val classDeclaration = stubClassDeclarationWithConstructor()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec.superclass.toString()

        assertThat(actual).contains(ItemSelectedEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that info property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclarationWithConstructor()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "info" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that constructor parameters are added as parameters`() {
        val intParam = "expected1"
        val stringParam = "expected2"
        val longParam = "expected3"

        val classDeclaration = stubClassDeclarationWithConstructor(
            constructorParameters = mapOf(
                intParam to Int::class,
                stringParam to String::class,
                longParam to Long::class,
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .primaryConstructor
            ?.parameters
            ?.map { it.name }

        assertThat(actual).contains(intParam)
        assertThat(actual).contains(stringParam)
        assertThat(actual).contains(longParam)
    }

    @Test
    internal fun `test that constructor parameters are added to info map`() {
        val intParam = "expected1"
        val stringParam = "expected2"
        val longParam = "expected3"

        val classDeclaration = stubClassDeclarationWithConstructor(
            constructorParameters = mapOf(
                intParam to Int::class,
                stringParam to String::class,
                longParam to Long::class,
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "info" }

        assertThat(actual.initializer.toString()).contains("\"$intParam\" to $intParam")
        assertThat(actual.initializer.toString()).contains("\"$stringParam\" to $stringParam")
        assertThat(actual.initializer.toString()).contains("\"$longParam\" to $longParam")
    }

    @Test
    internal fun `test that static value parameters are not added to constructor parameters`() {
        val expectedParameter = "expected"
        val intParam = "notExpected1"
        val stringParam = "notExpected2"
        val longParam = "notExpected3"

        val classDeclaration = stubClassDeclarationWithConstructor(
            constructorParameters = mapOf(expectedParameter to String::class),
            staticValueParameters = mapOf(
                intParam to "12",
                stringParam to "string",
                longParam to "1234",
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .primaryConstructor
            ?.parameters
            ?.map { it.name }

        assertThat(actual).doesNotContain(intParam)
        assertThat(actual).doesNotContain(stringParam)
        assertThat(actual).doesNotContain(longParam)
    }

    @Test
    internal fun `test that static valued parameters are added to the info map property`() {
        val expectedKey1 = "expected1"
        val expectedValue1 = "expected1Value"
        val expectedKey2 = "expected2"
        val expectedValue2 = "expected2Value"
        val expectedKey3 = "expected3"
        val expectedValue3 = "expected3Value"

        val classDeclaration = stubClassDeclarationWithConstructor(
            staticValueParameters = mapOf(
                expectedKey1 to expectedValue1,
                expectedKey2 to expectedValue2,
                expectedKey3 to expectedValue3,
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "info" }

        assertThat(actual.initializer.toString()).contains("\"$expectedKey1\" to \"$expectedValue1\"")
        assertThat(actual.initializer.toString()).contains("\"$expectedKey2\" to \"$expectedValue2\"")
        assertThat(actual.initializer.toString()).contains("\"$expectedKey3\" to \"$expectedValue3\"")
    }

    @Test
    internal fun `test that if no parameters are present info is an empty map`() {
        val classDeclaration = stubClassDeclarationWithConstructor()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "info" }

        assertThat(actual.initializer.toString().trim()).isEqualTo("emptyMap()")
    }

    override fun stubClassDeclaration(className: String) =
        stubClassDeclarationWithConstructor(className)

    private fun stubClassDeclarationWithConstructor(
        className: String = "name",
        constructorParameters: Map<String, KClass<*>>? = null,
        staticValueParameters: Map<String, String>? = null,
    ): KSClassDeclaration {
        val name = className.mockShortName()
        val constructor = stubConstructor(constructorParameters, staticValueParameters)
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { primaryConstructor }.thenReturn(constructor)
            on { getAllProperties() }.thenReturn(emptySequence())
        }
    }

    private fun stubConstructor(
        parameterTypes: Map<String, KClass<*>>?,
        staticValueParameters: Map<String, String>?,
    ): KSFunctionDeclaration? {
        if (parameterTypes.isNullOrEmpty() && staticValueParameters.isNullOrEmpty()) return null
        val annotationName = StaticValue::class.java.simpleName.mockShortName()

        val nonStaticParameters = stubNonStaticParameters(parameterTypes) ?: emptyList()
        val staticParameters =
            stubStaticValueParameters(staticValueParameters, annotationName) ?: emptyList()

        val constructor = mock<KSFunctionDeclaration> {
            on { parameters }.thenReturn(nonStaticParameters + staticParameters)
        }
        return constructor
    }

    private fun stubStaticValueParameters(
        staticValueParameters: Map<String, String>?,
        annotationName: KSName,
    ): List<KSValueParameter>? {
        val parameterMap = staticValueParameters?.map { (key, paramValue) ->
            val paramName = key.mockShortName()
            val argumentValue = mock<KSValueArgument> {
                on { value }.thenReturn(paramValue)
            }
            val staticValueAnnotation = mock<KSAnnotation> {
                on { shortName }.thenReturn(annotationName)
                on { arguments }.thenReturn(listOf(argumentValue))
            }
            val valueParam = mock<KSValueParameter> {
                on { name }.thenReturn(paramName)
                on { annotations }.thenReturn(sequenceOf(staticValueAnnotation))
            }
            valueParam to ParameterSpec.builder(name = key, type = String::class).build()
        }?.toMap()

        constructorParameterMapper.stub {
            parameterMap?.forEach { (key, value) ->
                on { invoke(key) }.thenReturn(value)
            }
        }
        return parameterMap?.keys?.toList()
    }

    private fun stubNonStaticParameters(parameterTypes: Map<String, KClass<*>>?): List<KSValueParameter>? {
        val parameterMap = parameterTypes?.map { (key, value) ->
            val paramName = key.mockShortName()
            val valueParam = mock<KSValueParameter> {
                on { name }.thenReturn(paramName)
                on { annotations }.thenReturn(emptySequence())
            }
            valueParam to ParameterSpec.builder(name = key, type = value).build()
        }?.toMap()

        constructorParameterMapper.stub {
            parameterMap?.forEach { (key, value) ->
                on { invoke(key) }.thenReturn(value)
            }
        }
        return parameterMap?.keys?.toList()
    }
}