package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSValueArgument
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest
import mega.privacy.mobile.analytics.processor.mockShortName
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.KStubbing
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LegacyEventVisitorTest :
    AnalyticsVisitorTest<LegacyEventVisitor, GenerateLegacyIdRequest>() {

    override fun initialiseUnderTest(idGenerator: IdGenerator<GenerateLegacyIdRequest>): LegacyEventVisitor {
        return LegacyEventVisitor(idGenerator)
    }

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

        assertThat(actual).contains(LegacyEventIdentifier::class.qualifiedName)
    }

    override fun stubGenerator(): KStubbing<IdGenerator<GenerateLegacyIdRequest>>.(IdGenerator<GenerateLegacyIdRequest>) -> Unit =
        {
            on { invoke(any()) }.thenAnswer {
                val request = it.arguments[0] as GenerateLegacyIdRequest
                mapOf(request.eventName to request.legacyId)
            }
        }

    override fun stubClassDeclaration(className: String) =
        stubClassDeclarationWithAnnotation(className)

    private fun stubClassDeclarationWithAnnotation(
        className: String,
        identifier: Int = 654321,
    ): KSClassDeclaration {
        val annotation = stubAnnotation(identifier)
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock {
            on { qualifiedName }.thenReturn(name)
            on { annotations }.thenReturn(sequenceOf(annotation))
        }
    }

    private fun stubAnnotation(identifier: Int): KSAnnotation {
        val annotationName =
            LegacyEvent::class.java.simpleName.mockShortName()
        val identifierArgumentName = LegacyEvent::eventId.name.mockShortName()
        val args = listOf(
            mock<KSValueArgument> {
                on { name }.thenReturn(identifierArgumentName)
                on { value }.thenReturn(identifier)
            },
        )
        val annotation = mock<KSAnnotation> {
            on { shortName }.thenReturn(annotationName)
            on { arguments }.thenReturn(args)
        }
        return annotation
    }
}