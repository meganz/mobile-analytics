package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

abstract class AnalyticsVisitorTest<T : AnalyticsEventVisitor> {
    protected lateinit var underTest: T

    protected val eventIdentifier = 42
    protected val idGenerator = mock<IdGenerator> {
        on { invoke(any(), any()) }.thenAnswer { mapOf(it.arguments[0] to eventIdentifier) }
    }

    @BeforeEach
    internal fun setUp() {
        underTest = initialiseUnderTest(idGenerator)
    }

    abstract fun initialiseUnderTest(idGenerator: IdGenerator): T

    @Test
    internal fun `test that exception is thrown if no name is present`() {
        val classDeclaration = mock<KSClassDeclaration> { on { qualifiedName }.thenReturn(null) }
        assertThrows<VisitorException> {
            underTest.visitClassDeclaration(
                classDeclaration = classDeclaration,
                data = EventData(emptyMap()),
            )
        }
    }

    @Test
    internal fun `test that the new class name is added to the id map`() {
        val expected = "Expected"
        val classDeclaration = stubClassDeclaration(className = expected)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
        )

        assertThat(actual.spec.name).isEqualTo(expected)
    }

    @Test
    internal fun `test that event type is correct`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        )

        assertThat(actual.spec.kind).isEqualTo(TypeSpec.Kind.OBJECT)
    }

    @Test
    internal fun `test that event name property is added with the correct value`() {
        val name = "Expected"
        val expected = "\"$name\""
        val classDeclaration = stubClassDeclaration(className = name)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "uniqueIdentifier" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    abstract fun stubClassDeclaration(className: String = "name"): KSClassDeclaration

}