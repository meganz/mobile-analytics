package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.eventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.expectedAppIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.testEventIdentifier
import kotlin.test.Test
import kotlin.test.assertTrue

class ItemSelectedEventTest {
    private val expectedInfo = mapOf("info" to 3)
    private val expectedEventName = "eventName"
    private val fakeIdentifier = object : ItemSelectedEventIdentifier {
        override val info: Map<String, Any>
            get() = expectedInfo
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }
    private val underTest = ItemSelectedEvent(
        eventIdentifier = fakeIdentifier,
        viewId = null,
        appIdentifier = expectedAppIdentifier
    )

    @Test
    fun `test that event identifier is 8000`() {
        val expected = 8000
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
        assertTrue(actual.contains(expectedInfo.values.first().toString()))
    }
}