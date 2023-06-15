package mega.privacy.mobile.analytics.event.type

import mega.privacy.mobile.analytics.EventDataMapper
import mega.privacy.mobile.analytics.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.getPlatform
import kotlin.test.Test
import kotlin.test.assertEquals

class ScreenViewEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1

    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : ScreenViewEventIdentifier {
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }

    private val underTest = ScreenViewEvent(
        eventIdentifier = fakeIdentifier,
        viewId = "viewId"
    )

    @Test
    fun `test that event identifier is 0`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 0, actual = actual)
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