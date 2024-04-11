package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.eventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.expectedAppIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.testEventIdentifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MenuItemEventTest {
    private val expectedScreenName = "screenName"
    private val expectedMenuItem = "menuItem"
    private val expectedMenuType = "menuType"
    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : MenuItemEventIdentifier {
        override val menuItem: String
            get() = expectedMenuItem
        override val screenName: String?
            get() = expectedScreenName
        override val menuType: String?
            get() = expectedMenuType
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier
    }

    private val underTest = MenuItemEvent(
        eventIdentifier = fakeIdentifier,
        viewId = null,
        appIdentifier = expectedAppIdentifier
    )

    @Test
    fun `test that event identifier is 5000`() {
        val expected = 5000
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
        assertTrue(actual.contains(expectedMenuItem))
        assertTrue(actual.contains(expectedMenuType))
    }
}