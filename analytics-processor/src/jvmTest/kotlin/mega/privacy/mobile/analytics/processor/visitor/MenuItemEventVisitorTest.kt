package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MenuItemEventVisitorTest : AnalyticsVisitorTest<MenuItemEventVisitor>() {
    override fun initialiseUnderTest(idGenerator: IdGenerator) =
        MenuItemEventVisitor(idGenerator)

    @Test
    internal fun `test that event extends correct class`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec.superclass.toString()

        assertThat(actual).contains(MenuItemEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that menItem name property is added with the correct value`() {
        val menuItem = "Expected"
        val expectedMenuItemName = "\"$menuItem\""
        val classDeclaration = stubClassWithAnnotation(
            menuItem = menuItem
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["menuItem"].toString()).isEqualTo(expectedMenuItemName)
    }

    @Test
    internal fun `test that screen name property is added with the correct value`() {
        val screenName = "Expected"
        val expectedScreenName = "\"$screenName\""
        val classDeclaration = stubClassWithAnnotation(
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
    internal fun `test that menu type property is added with the correct value`() {
        val menuType = MenuItemEvent.MenuType.Item
        val expectedMenuType = "\"$menuType\""
        val classDeclaration = stubClassWithAnnotation(
            menuType = menuType
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["menuType"].toString()).isEqualTo(expectedMenuType)
    }

    @Test
    internal fun `test that screen name is nullable`() {
        val classDeclaration = stubClassWithAnnotation()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.type }

        assertThat(actual["screenName"]?.isNullable).isTrue()
    }

    @Test
    internal fun `test that empty screen name is added as null`() {
        val screenName = ""
        val expectedScreenName = "null"
        val classDeclaration = stubClassWithAnnotation(
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

    override fun stubClassDeclaration(className: String) =
        stubClassWithAnnotation(className)

    private fun stubClassWithAnnotation(
        className: String = "name",
        menuItem: String = "menuItem",
        screenName: String? = null,
        menuType: MenuItemEvent.MenuType = MenuItemEvent.MenuType.Toolbar,
    ): KSClassDeclaration {
        val annotation = stubAnnotation(menuItem, screenName, menuType)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(
        menuItem: String,
        screenName: String?,
        menuType: MenuItemEvent.MenuType,
    ): KSAnnotation {
        val annotationName =
            MenuItemEvent::class.java.simpleName.mockShortName()
        val menuItemName = MenuItemEvent::menuItem.name.mockShortName()
        val screenArgumentName = MenuItemEvent::screen.name.mockShortName()
        val menuTypeArgumentName = MenuItemEvent::menuType.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(menuItemName)
                on { value }.thenReturn(menuItem)
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(screenArgumentName)
                on { value }.thenReturn(screenName ?: "")
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(menuTypeArgumentName)
                on { value }.thenReturn(menuType)
            },
        )
        val annotation = mock<KSAnnotation> {
            on { shortName }.thenReturn(annotationName)
            on { arguments }.thenReturn(args)
        }
        return annotation
    }

}