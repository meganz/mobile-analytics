package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NavigationEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventIdentifier = 1

    private val expectedNavigationElementType = "navigationElementType"
    private val expectedDestination = "destination"

    private val expectedEventName = "eventName"

    private val fakeIdentifier = object : NavigationEventIdentifier() {
        override val navigationElementType: String?
            get() = expectedNavigationElementType
        override val destination: String?
            get() = expectedDestination
        override val eventName: String
            get() = expectedEventName
        override val uniqueIdentifier: Int
            get() = eventIdentifier

    }

    private val underTest = NavigationEvent(
        eventIdentifier = fakeIdentifier,
        viewId = null
    )

    @Test
    fun `test that event identifier is 4000`() {
        val actual = underTest.getEventIdentifier() - platformIdentifier - eventIdentifier
        assertEquals(expected = 4000, actual = actual)
    }

    @Test
    fun `test that data contains all event data`() {
        val mapper = object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                return eventData.values.joinToString { it.toString() }
            }
        }
        val actual = underTest.getEventMessage(mapper)

        assertTrue(actual.contains(expectedNavigationElementType))
        assertTrue(actual.contains(expectedDestination))
    }
}