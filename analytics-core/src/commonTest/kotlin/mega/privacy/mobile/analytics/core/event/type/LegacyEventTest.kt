package mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier
import mega.privacy.mobile.analytics.core.event.type.LegacyEvent
import kotlin.test.Test
import kotlin.test.assertEquals

class LegacyEventTest {

    private val legacyEventName = "LegacyEventName"
    private val eventIdentifier = 123456

    val fakeIdentifier = object : LegacyEventIdentifier {
        override val eventName: String = legacyEventName
        override val uniqueIdentifier: Int = eventIdentifier
    }

    private val underTest = LegacyEvent(
        viewId = null,
        appIdentifier = AppIdentifier(1),
        eventIdentifier = fakeIdentifier,
    )

    @Test
    fun `test that identifier is same as the unique identifier`() {
        assertEquals(expected = eventIdentifier, underTest.getEventIdentifier())
    }
}