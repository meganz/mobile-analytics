package mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
import mega.privacy.mobile.analytics.core.event.type.GestureEvent
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.eventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.expectedAppIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.testEventIdentifier
import kotlin.test.Test
import kotlin.test.assertTrue

class GestureEventTest {
    private val expectedScreenName = "screenName"
    private val expectedGestureName = "gestureName"
    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : GestureEventIdentifier {
        override val screenName: String?
            get() = expectedScreenName
        override val gestureName: String
            get() = expectedGestureName
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }

    private val underTest = GestureEvent(
        eventIdentifier = fakeIdentifier,
        viewId = null,
        appIdentifier = expectedAppIdentifier
    )

    @Test
    fun `test that event identifier is 9000`() {
        val expected = 9000
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
        assertTrue(actual.contains(expectedGestureName))
    }
}