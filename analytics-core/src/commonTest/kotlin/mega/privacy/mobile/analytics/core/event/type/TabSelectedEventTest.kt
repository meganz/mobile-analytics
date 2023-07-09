package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TabSelectedEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1
    private val expectedScreenName = "screenName"
    private val expectedTabName = "tabName"


    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : TabSelectedEventIdentifier() {
        override val screenName: String
            get() = expectedScreenName
        override val tabName: String
            get() = expectedTabName
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier
    }

    private val underTest = TabSelectedEvent(
        eventIdentifier = fakeIdentifier,
        viewId = ""
    )

    @Test
    fun `test that event identifier is 1000`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 1000, actual = actual)
    }

    @Test
    fun `test that data contains all event data`() {
        val mapper = object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                return eventData.values.joinToString { it.toString() }
            }
        }
        val actual = underTest.getEventMessage(mapper)
        assertTrue(actual.contains(expectedScreenName))
        assertTrue(actual.contains(expectedTabName))
    }
}