package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemSelectedEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1
    private val expectedInfo = mapOf("info" to 3)


    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : ItemSelectedEventIdentifier() {
        override val info: Map<String, Any>
            get() = expectedInfo
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }

    private val underTest = ItemSelectedEvent(
        eventIdentifier = fakeIdentifier,
        viewId = null
    )

    @Test
    fun `test that event identifier is 8000`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 8000, actual = actual)
    }

    @Test
    fun `test that data contains all event data`() {
        val mapper = object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                return eventData.values.joinToString { it.toString() }
            }
        }
        val actual = underTest.getEventMessage(mapper)
        assertTrue(actual.contains(expectedInfo.values.first().toString()))
    }
}