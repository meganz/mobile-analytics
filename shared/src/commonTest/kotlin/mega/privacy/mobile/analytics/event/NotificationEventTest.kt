package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.EventDataMapper
import mega.privacy.mobile.analytics.getPlatform
import mega.privacy.mobile.analytics.identifier.NotificationEventIdentifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class NotificationEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1

    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : NotificationEventIdentifier {
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }

    private val underTest = NotificationEvent(
        eventIdentifier = fakeIdentifier,
    )

    @Test
    fun `test that event identifier is 6000`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 6000, actual = actual)
    }

    @Test
    fun `test that data contains no event data`() {
        val mapper = object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                return eventData.values.joinToString { it.toString() }
            }
        }
        val actual = underTest.getEventMessage(mapper)
        assertEquals(expectedEventName, actual.trim())
    }
}