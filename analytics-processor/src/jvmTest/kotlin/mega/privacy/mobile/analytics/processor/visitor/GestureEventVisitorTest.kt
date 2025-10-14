package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import mega.privacy.mobile.analytics.annotations.GestureEvent
import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GestureEventVisitorTest :
    SimpleIdAnalyticsVisitorTest<GestureEventVisitor>() {

    override fun initialiseUnderTest(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        GestureEventVisitor(idGenerator)


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

        assertThat(actual).contains(GestureEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that gesture name property is added with the correct value`() {
        val gestureName = "Expected"
        val expectedGestureName = "\"$gestureName\""
        val classDeclaration = stubClassWithAnnotation(
            gestureName = gestureName
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["gestureName"].toString()).isEqualTo(expectedGestureName)
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
        gestureName: String = "gesture",
        screenName: String? = null,
    ): KSClassDeclaration {
        val annotation = stubAnnotation(gestureName, screenName)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(
        gestureName: String,
        screenName: String?,
    ): KSAnnotation {
        val annotationName =
            GestureEvent::class.java.simpleName.mockShortName()
        val screenArgumentName = GestureEvent::screen.name.mockShortName()
        val gestureArgumentName = GestureEvent::gesture.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(gestureArgumentName)
                on { value }.thenReturn(gestureName)
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(screenArgumentName)
                on { value }.thenReturn(screenName ?: "")
            },
        )
        val annotation = mock<KSAnnotation> {
            on { shortName }.thenReturn(annotationName)
            on { arguments }.thenReturn(args)
        }
        return annotation
    }

}