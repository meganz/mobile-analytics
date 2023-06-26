package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.TabSelectedEventData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TabSelectedVisitorTest {
    private lateinit var underTest: TabSelectedVisitor
    private val eventIdentifier = 42

    private val idGenerator = mock<IdGenerator> {
        on { invoke(any(), any()) }.thenAnswer { mapOf(it.arguments[0] to eventIdentifier) }
    }

    @BeforeEach
    internal fun setUp() {
        underTest = TabSelectedVisitor(idGenerator)
    }

    @Test
    internal fun `test that exception is thrown if no name is present`() {
        val classDeclaration = mock<KSClassDeclaration> { on { qualifiedName }.thenReturn(null) }
        assertThrows<VisitorException> {
            underTest.visitClassDeclaration(
                classDeclaration = classDeclaration,
                data = TabSelectedEventData(emptyMap()),
            )
        }
    }

    @Test
    internal fun `test that the new class name is added to the id map`() {
        val expected = "Expected"
        val classDeclaration = stubClassDeclaration(className = expected)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
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
            data = TabSelectedEventData(emptyMap()),
        )

        assertThat(actual.spec.name).isEqualTo(expected)
    }

    @Test
    internal fun `test that event type is correct`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        )

        assertThat(actual.spec.kind).isEqualTo(TypeSpec.Kind.OBJECT)
    }

    @Test
    internal fun `test that event implements correct interface`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .superinterfaces
            .keys
            .map { it.toString() }

        assertThat(actual).contains(TabSelectedEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that event name property is added with the correct value`() {
        val name = "Expected"
        val expected = "\"$name\""
        val classDeclaration = stubClassDeclaration(className = name)
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
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
            data = TabSelectedEventData(emptyMap()),
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
            data = TabSelectedEventData(emptyMap()),
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
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "uniqueIdentifier" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that tab name property is added with the correct value`() {
        val tabName = "Expected"
        val expectedTabName = "\"$tabName\""
        val classDeclaration = stubClassDeclaration(
            tabName = tabName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["tabName"].toString()).isEqualTo(expectedTabName)
    }

    @Test
    internal fun `test that tab name property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "tabName" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that screen name property is added with the correct value`() {
        val screenName = "Expected"
        val expectedScreenName = "\"$screenName\""
        val classDeclaration = stubClassDeclaration(
            screenName = screenName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["screenName"].toString()).isEqualTo(expectedScreenName)
    }

    @Test
    internal fun `test that screen name property is declared as an overridden property`() {
        val classDeclaration = stubClassDeclaration()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = TabSelectedEventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "screenName" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    private fun stubClassDeclaration(
        className: String = "name",
        tabName: String = "tab",
        screenName: String = "screen",
    ): KSClassDeclaration {
        val annotation = stubAnnotation(tabName, screenName)

        val name = className.mockShortName()
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(
        tabName: String,
        screenName: String,
    ): KSAnnotation {
        val annotationName =
            TabSelectedEvent::class.java.simpleName.mockShortName()
        val tabArgumentName = TabSelectedEvent::tabName.name.mockShortName()
        val screenArgumentName = TabSelectedEvent::screenName.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(tabArgumentName)
                on { value }.thenReturn(tabName)
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(screenArgumentName)
                on { value }.thenReturn(screenName)
            }
        )
        val annotation = mock<KSAnnotation> {
            on { shortName }.thenReturn(annotationName)
            on { arguments }.thenReturn(args)
        }
        return annotation
    }

}