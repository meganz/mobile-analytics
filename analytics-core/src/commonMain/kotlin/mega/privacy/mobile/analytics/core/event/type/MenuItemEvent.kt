package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier


/**
 * Menu item event
 *
 * @property eventIdentifier
 * @property viewId
 */
class MenuItemEvent(
    override val eventIdentifier: MenuItemEventIdentifier,
    override val viewId: String?,
    override val appIdentifier: AppIdentifier
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 5000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "screen_name" to eventIdentifier.screenName,
                "menu_item" to eventIdentifier.menuItem,
                "menu_type" to eventIdentifier.menuType,
            )
        }
}