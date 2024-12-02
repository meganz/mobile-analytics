package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.eventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.expectedAppIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.testEventIdentifier
import kotlin.test.Test
import kotlin.test.assertTrue

class ButtonPressedEventTest {
    private val expectedButtonName = "buttonName"
    private val expectedScreenName = "screenName"
    private val expectedDialogName = "dialogName"
    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : ButtonPressedEventIdentifier {
        override val buttonName: String
            get() = expectedButtonName
        override val screenName: String?
            get() = expectedScreenName
        override val dialogName: String?
            get() = expectedDialogName
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier
    }

    private val underTest = ButtonPressedEvent(
        eventIdentifier = fakeIdentifier,
        appIdentifier = expectedAppIdentifier,
        viewId = null
    )

    @Test
    fun `test that event identifier is 2000`() {
        val expected = 2000
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
        assertTrue(actual.contains(expectedButtonName))
        assertTrue(actual.contains(expectedScreenName))
        assertTrue(actual.contains(expectedDialogName))
    }
}