package mega.privacy.mobile.analytics.processor.visitor

import com.google.common.truth.Truth.assertThat
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSName
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class NotificationEventVisitorTest : AnalyticsVisitorTest<NotificationEventVisitor>() {

    @Test
    internal fun `test that event extends correct class`() {
        val classDeclaration = stubClassDeclaration()

        val actual = underTest.visitClassDeclaration(
            classDeclaration = classDeclaration,
            data = EventData(emptyMap()),
        ).spec.superclass.toString()

        assertThat(actual).contains(NotificationEventIdentifier::class.qualifiedName)
    }

    override fun initialiseUnderTest(idGenerator: IdGenerator) =
        NotificationEventVisitor(idGenerator)

    override fun stubClassDeclaration(className: String): KSClassDeclaration {
        val name = mock<KSName> { on { getShortName() }.thenReturn(className) }
        return mock { on { qualifiedName }.thenReturn(name) }
    }
}