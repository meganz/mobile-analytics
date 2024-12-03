package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class NotificationEventVisitorTest :
    SimpleIdAnalyticsVisitorTest<NotificationEventVisitor>() {

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

        assertThat(actual).contains(NotificationEventIdentifier::class.qualifiedName)
    }

    override fun initialiseUnderTest(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        NotificationEventVisitor(idGenerator)

    override fun stubClassDeclaration(className: String): KSClassDeclaration {
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock { on { qualifiedName }.thenReturn(name) }
    }
}