package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Fixes the values that make up a sent event ID and the payload keys of each event type.
 *
 * The backend and the dashboards decode stored events with these values, so a change to one of
 * them corrupts the data of every app that updates the library. The platform bases are fixed by
 * the PlatformTest of each platform, because common code sees the base of the platform it runs on.
 */
class EventIdentityPinningTest {

    private val uniqueId = 7
    private val app = AppIdentifier(3)

    private val events = mapOf(
        "ScreenView" to ScreenViewEvent(
            object : ScreenViewEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
            }, "view", app
        ),
        "TabSelected" to TabSelectedEvent(
            object : TabSelectedEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val screenName = "s"
                override val tabName = "t"
            }, "view", app
        ),
        "ButtonPress" to ButtonPressedEvent(
            object : ButtonPressedEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val buttonName = "b"
                override val screenName = "s"
                override val dialogName = "d"
            }, null, app
        ),
        "DialogDisplayed" to DialogDisplayedEvent(
            object : DialogDisplayedEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val screenName = "s"
                override val dialogName = "d"
            }, null, app
        ),
        "Navigation" to NavigationEvent(
            object : NavigationEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val navigationElementType = "n"
                override val destination = "d"
            }, null, app
        ),
        "MenuItem" to MenuItemEvent(
            object : MenuItemEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val menuItem = "m"
                override val screenName = "s"
                override val menuType = "t"
            }, null, app
        ),
        "Notification" to NotificationEvent(
            object : NotificationEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
            }, app
        ),
        "General" to GeneralEvent(
            object : GeneralEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val info = mapOf("key" to 1)
            }, null, app
        ),
        "ItemSelected" to ItemSelectedEvent(
            object : ItemSelectedEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val info = mapOf("key" to 1)
            }, null, app
        ),
        "Gesture" to GestureEvent(
            object : GestureEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
                override val screenName = "s"
                override val gestureName = "g"
            }, null, app
        ),
    )

    @Test
    fun `test that each event type adds its fixed type offset to the event id`() {
        val offsets = events.mapValues { (_, event) ->
            event.getEventIdentifier() - getPlatform().baseIdentifier - uniqueId - app.identifier
        }

        assertEquals(
            mapOf(
                "ScreenView" to 0,
                "TabSelected" to 1000,
                "ButtonPress" to 2000,
                "DialogDisplayed" to 3000,
                "Navigation" to 4000,
                "MenuItem" to 5000,
                "Notification" to 6000,
                "General" to 7000,
                "ItemSelected" to 8000,
                "Gesture" to 9000,
            ),
            offsets,
        )
    }

    @Test
    fun `test that each event type sends its fixed payload keys`() {
        val keys = events.mapValues { (_, event) -> payloadKeys(event) }

        assertEquals(
            mapOf(
                "ScreenView" to emptySet(),
                "TabSelected" to setOf("screen_name", "tab_name"),
                "ButtonPress" to setOf("screen_name", "dialog_name", "button_name"),
                "DialogDisplayed" to setOf("screen_name", "dialog_name"),
                "Navigation" to setOf("navigation_element_type", "destination"),
                "MenuItem" to setOf("screen_name", "menu_item", "menu_type"),
                "Notification" to emptySet(),
                "General" to setOf("key"),
                "ItemSelected" to setOf("key"),
                "Gesture" to setOf("screen_name", "gesture_name"),
            ),
            keys,
        )
    }

    @Test
    fun `test that a legacy event id is its unique identifier only`() {
        val event = LegacyEvent(
            object : LegacyEventIdentifier {
                override val eventName = "Name"
                override val uniqueIdentifier = uniqueId
            }, null, app
        )

        assertEquals(uniqueId, event.getEventIdentifier())
        assertEquals(emptySet(), payloadKeys(event))
    }

    @Test
    fun `test that the app identifier adds its id times 10 000 to the event id`() {
        assertEquals((0..9).map { it * 10_000 }, (0..9).map { AppIdentifier(it).identifier })
    }

    private fun payloadKeys(event: AnalyticsEvent): Set<String> {
        var keys = emptySet<String>()
        event.getEventMessage(object : EventDataMapper {
            override fun mapData(eventData: Map<String, Any?>): String {
                keys = eventData.keys
                return ""
            }
        })
        return keys
    }
}
