package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class NavigationEventVisitorTest : AnalyticsVisitorTest<NavigationEventVisitor>() {
    override fun initialiseUnderTest(idGenerator: IdGenerator) =
        NavigationEventVisitor(idGenerator)

    @Test
    internal fun `test that event extends correct class`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec.superclass.toString()

        assertThat(actual).contains(NavigationEventIdentifier::class.qualifiedName)
    }

    @Test
    internal fun `test that destination name property is added with the correct value`() {
        val destination = "Expected"
        val expectedDestinationName = "\"$destination\""
        val classDeclaration = stubClassWithAnnotation(
            destination = destination
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["destination"].toString()).isEqualTo(expectedDestinationName)
    }

    @Test
    internal fun `test that navigation element type property is added with the correct value`() {
        val navigationElementType = NavigationEvent.NavigationElementType.Bottom
        val expectedNavigationElementType = "\"$navigationElementType\""
        val classDeclaration = stubClassWithAnnotation(
            navigationElementType = navigationElementType
        )
        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec
            .propertySpecs
            .associate { it.name to it.initializer }

        assertThat(actual["navigationElementType"].toString()).isEqualTo(expectedNavigationElementType)
    }

    override fun stubClassDeclaration(className: String) =
        stubClassWithAnnotation(className)

    private fun stubClassWithAnnotation(
        className: String = "name",
        destination: String = "destination",
        navigationElementType: NavigationEvent.NavigationElementType = NavigationEvent.NavigationElementType.Other,
    ): KSClassDeclaration {
        val annotation = stubAnnotation(destination, navigationElementType)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(
        destination: String,
        menuType: NavigationEvent.NavigationElementType,
    ): KSAnnotation {
        val annotationName =
            NavigationEvent::class.java.simpleName.mockShortName()
        val destinationName = NavigationEvent::destination.name.mockShortName()
        val navigationElementTypeName = NavigationEvent::navigationElementType.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(destinationName)
                on { value }.thenReturn(destination)
            },
            mock<KSValueArgument> {
                on { name }.thenReturn(navigationElementTypeName)
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