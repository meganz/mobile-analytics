package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ButtonPressVisitorTest : SimpleIdAnalyticsVisitorTest<ButtonPressVisitor>() {
    override fun initialiseUnderTest(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        ButtonPressVisitor(idGenerator)

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

        assertThat(actual).contains(ButtonPressedEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that button name property is added with the correct value`() {
        val buttonName = "Expected"
        val expectedButtonName = "\"$buttonName\""
        val classDeclaration = stubClassWithAnnotation(
            buttonName = buttonName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["buttonName"].toString()).isEqualTo(expectedButtonName)
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
    internal fun `test that dialog name property is added with the correct value`() {
        val dialogName = "Expected"
        val expectedDialogName = "\"$dialogName\""
        val classDeclaration = stubClassWithAnnotation(
            dialogName = dialogName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["dialogName"].toString()).isEqualTo(expectedDialogName)
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
    internal fun `test that dialog name is nullable`() {
        val classDeclaration = stubClassWithAnnotation()
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.type }

        assertThat(actual["dialogName"]?.isNullable).isTrue()
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

    @Test
    internal fun `test that empty dialog name is added as null`() {
        val dialogName = ""
        val expectedDialogName = "null"
        val classDeclaration = stubClassWithAnnotation(
            dialogName = dialogName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["dialogName"].toString()).isEqualTo(expectedDialogName)
    }

    override fun stubClassDeclaration(className: String) =
        stubClassWithAnnotation(className)

    private fun stubClassWithAnnotation(
        className: String = "name",
        buttonName: String = "buttonName",
        screenName: String? = null,
        dialogName: String? = null,
    ): KSClassDeclaration {
        val annotation = stubAnnotation(buttonName, screenName, dialogName)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(
        buttonName: String,
        screenName: String?,
        dialogName: String?,
    ): KSAnnotation {
        val annotationName =
            ButtonPressEvent::class.java.simpleName.mockShortName()
        val buttonArgumentName = ButtonPressEvent::buttonName.name.mockShortName()
        val screenArgumentName = ButtonPressEvent::screen.name.mockShortName()
        val dialogArgumentName = ButtonPressEvent::dialog.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(buttonArgumentName)
                on { value }.thenReturn(buttonName)
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(screenArgumentName)
                on { value }.thenReturn(screenName ?: "")
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(dialogArgumentName)
                on { value }.thenReturn(dialogName ?: "")
            },
        )
        val annotation = mock<KSAnnotation> {
            on { shortName }.thenReturn(annotationName)
            on { arguments }.thenReturn(args)
        }
        return annotation
    }

}