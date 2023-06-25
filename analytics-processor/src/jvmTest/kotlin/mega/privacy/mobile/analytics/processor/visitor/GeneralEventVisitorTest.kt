package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.*
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSPropertyGetter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.generator.mockShortName
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.GeneralEventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import kotlin.reflect.KClass

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GeneralEventVisitorTest {
    private lateinit var underTest: GeneralEventVisitor
    private val eventIdentifier = 42

    private val idGenerator = mock<IdGenerator> {
        on { invoke(any(), any()) }.thenAnswer { mapOf(it.arguments[0] to eventIdentifier) }
    }

    private val constructorParameterMapper = mock<ConstructorParameterMapper>()

    @BeforeEach
    internal fun setUp() {
        underTest = GeneralEventVisitor(
            idGenerator = idGenerator,
            constructorParameterMapper = constructorParameterMapper
        )
    }

    @Test
    internal fun `test that exception is thrown if no name is present`() {
        val classDeclaration = mock<KSClassDeclaration> { on { qualifiedName }.thenReturn(null) }
        assertThrows<VisitorException> {
            underTest.visitClassDeclaration(
                classDeclaration = classDeclaration,
                data = GeneralEventData(emptyMap()),
            )
        }
    }

    @Test
    internal fun `test that the new class name is added to the id map`() {
        val expected = "Expected"
        val classDeclaration = stubClassDeclaration(className = expected)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        )

        assertThat(actual.idMap).containsKey(expected)
    }

    @Test
    internal fun `test that event name is correct`() {
        val className = "AnnotatedName"
        val expected = "${className}Event"

        val classDeclaration = stubClassDeclaration(className = className)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        )

        assertThat(actual.spec.name).isEqualTo(expected)
    }

    @Test
    internal fun `test that event type is correct`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        )

        assertThat(actual.spec.kind).isEqualTo(TypeSpec.Kind.OBJECT)
    }

    @Test
    internal fun `test that event implements correct interface`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .superinterfaces
            .keys
            .map { it.toString() }

        assertThat(actual).contains(GeneralEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that event name property is added with the correct value`() {
        val name = "Expected"
        val expected = "\"$name\""
        val classDeclaration = stubClassDeclaration(className = name)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["eventName"].toString()).isEqualTo(expected)
    }

    @Test
    internal fun `test that event name property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "eventName" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that event identifier is added with the value returned by the id generator`() {
        val expected = eventIdentifier
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["uniqueIdentifier"].toString()).isEqualTo(expected.toString())
    }

    @Test
    internal fun `test that event id property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "uniqueIdentifier" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that info property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
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

        val classDeclaration = stubClassDeclaration(
            constructorParameters = mapOf(
                intParam to Int::class,
                stringParam to String::class,
                longParam to Long::class,
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
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

        val classDeclaration = stubClassDeclaration(
            constructorParameters = mapOf(
                intParam to Int::class,
                stringParam to String::class,
                longParam to Long::class,
            )
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = GeneralEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "info" }

        assertThat(actual.initializer.toString()).contains("\"$intParam\" to $intParam")
        assertThat(actual.initializer.toString()).contains("\"$stringParam\" to $stringParam")
        assertThat(actual.initializer.toString()).contains("\"$longParam\" to $longParam")
    }

//    @Test
//    internal fun `test that fields without values throw an exception`() {
//        val classDeclaration = stubClassDeclaration(fields = mapOf("emptyField" to null))
//        assertThrows<VisitorException> {
//            underTest.visitClassDeclaration(
//                classDeclaration = classDeclaration,
//                data = GeneralEventData(emptyMap()),
//            )
//        }
//    }

    private fun stubClassDeclaration(
        className: String = "name",
        constructorParameters: Map<String, KClass<*>>? = null,
        fields: Map<String, Any?>? = null,
    ): KSClassDeclaration {
        val name = className.mockShortName()
        val constructor = constructorParameters?.let { stubConstructor(it) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { primaryConstructor }.thenReturn(constructor)
//            on { getAllProperties() }.thenReturn(stubFields(fields))
        }
    }

//    private fun stubFields(fields: Map<String, Any?>?): Sequence<KSPropertyDeclaration>? {
//        if (fields.isNullOrEmpty()) return emptySequence()
//
//        fields.forEach { (fieldName, fieldValue) ->
//            val shortName = fieldName.mockShortName()
//            val getterValue = mock<KSTypeReference>{
//                on {  }
//            }
//            val propertyGetter = mock<KSPropertyGetter>{
//                on { returnType }
//            }
//            mock<KSPropertyDeclaration> {
//                on { qualifiedName }.thenReturn(shortName)
//                on { getter }
//            }
//        }
//    }

    private fun stubConstructor(
        parameterTypes: Map<String, KClass<*>>,
    ): KSFunctionDeclaration {
        val parameterMap = parameterTypes.map { (key, value) ->
            val paramName = key.mockShortName()
            val valueParam = mock<KSValueParameter> { on { name }.thenReturn(paramName) }
            valueParam to ParameterSpec.builder(name = key, type = value).build()
        }.toMap()

        constructorParameterMapper.stub {
            parameterMap.forEach { (key, value) ->
                on { invoke(key) }.thenReturn(value)
            }
        }
        val constructor = mock<KSFunctionDeclaration> {
            on { parameters }.thenReturn(parameterMap.keys.toList())
        }
        return constructor
    }
}