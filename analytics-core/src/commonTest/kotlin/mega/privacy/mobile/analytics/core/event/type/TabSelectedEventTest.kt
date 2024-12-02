package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.eventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.expectedAppIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.testEventIdentifier
import kotlin.test.Test
import kotlin.test.assertTrue

class TabSelectedEventTest {
    private val expectedScreenName = "screenName"
    private val expectedTabName = "tabName"
    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : TabSelectedEventIdentifier {
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
        viewId = "",
        appIdentifier = expectedAppIdentifier
    )

    @Test
    fun `test that event identifier is 1000`() {
        val expected = 1000
        val eventUnderTest = underTest
        testEventIdentifier(eventUnderTest, expected)
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