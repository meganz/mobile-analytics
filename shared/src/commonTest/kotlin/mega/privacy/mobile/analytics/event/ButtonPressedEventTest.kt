package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.EventDataMapper
import mega.privacy.mobile.analytics.getPlatform
import mega.privacy.mobile.analytics.identifier.ButtonPressedEventIdentifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ButtonPressedEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1
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
        viewId = null
    )

    @Test
    fun `test that event identifier is 2000`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 2000, actual = actual)
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