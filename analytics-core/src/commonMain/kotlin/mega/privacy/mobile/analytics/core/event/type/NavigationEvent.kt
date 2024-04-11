package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier


/**
 * Navigation event
 *
 * @property eventIdentifier
 * @property viewId
 */
class NavigationEvent(
    override val eventIdentifier: NavigationEventIdentifier,
    override val viewId: String?,
    override val appIdentifier: AppIdentifier
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 4000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "navigation_element_type" to eventIdentifier.navigationElementType,
                "destination" to eventIdentifier.destination,
            )
        }
}