package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class AnalyticsEventTest {
    private val platformIdentifier = getPlatform().baseIdentifier
    private val eventId = 100
    private val typeId = 200
    private val expectedName = "EventName"
    private val data = mapOf("data" to null)

    private val fakeEventIdentifier = object : EventIdentifier {
        override val eventName: String
            get() = expectedName
        override val uniqueIdentifier: Int
            get() = eventId
    }

    private val fakeAppIdentifier = AppIdentifier(1)

    private val underTest = object : AnalyticsEvent() {
        override val viewId: String?
            get() = null
        override val appIdentifier: AppIdentifier
            get() = fakeAppIdentifier
        override val eventIdentifier: EventIdentifier
            get() = fakeEventIdentifier
        override val eventTypeIdentifier: Int
            get() = typeId
        override val eventData: Map<String, Any?>
            get() = data
    }

    @Test
    fun `test that the identifier combines platform and event type and unique identifiers`() {
        val expected = platformIdentifier + typeId + eventId + fakeAppIdentifier.identifier
        assertEquals(expected = expected, actual = underTest.getEventIdentifier())
    }

    @Test
    fun `test that message string contains name and mapped data`() {
        val expectedDataString = "Expected data string"
        val mapper = object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                return expectedDataString
            }
        }
        val actual = underTest.getEventMessage(mapper)

        assertTrue(actual.startsWith(expectedName))
        assertTrue(actual.contains(expectedDataString))
    }
}