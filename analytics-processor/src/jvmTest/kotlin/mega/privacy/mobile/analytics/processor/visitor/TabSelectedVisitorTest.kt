package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.kotlinpoet.KModifier
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TabSelectedVisitorTest : AnalyticsVisitorTest<TabSelectedVisitor>() {
    override fun initialiseUnderTest(idGenerator: IdGenerator) =
        TabSelectedVisitor(idGenerator)

    @Test
    internal fun `test that event implements correct interface`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .superinterfaces
            .keys
            .map { it.toString() }

        assertThat(actual).contains(TabSelectedEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that tab name property is added with the correct value`() {
        val tabName = "Expected"
        val expectedTabName = "\"$tabName\""
        val classDeclaration = stubClassDeclarationWithAnnotation(
            tabName = tabName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "tabName" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    @Test
    internal fun `test that screen name property is added with the correct value`() {
        val screenName = "Expected"
        val expectedScreenName = "\"$screenName\""
        val classDeclaration = stubClassDeclarationWithAnnotation(
            screenName = screenName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
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
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .first { it.name == "screenName" }

        assertThat(actual.modifiers).contains(KModifier.OVERRIDE)
    }

    override fun stubClassDeclaration(className: String) =
        stubClassDeclarationWithAnnotation(className)

    private fun stubClassDeclarationWithAnnotation(
        className: String = "name",
        tabName: String = "tab",
        screenName: String = "screen",
    ): KSClassDeclaration {
        val annotation = stubAnnotation(tabName, screenName)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
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