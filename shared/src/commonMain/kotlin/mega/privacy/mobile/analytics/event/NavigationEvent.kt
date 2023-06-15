package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.NavigationEventIdentifier


/**
 * Navigation event
 *
 * @property identifier
 * @property viewId
 * @constructor Create empty Navigation event
 */
class NavigationEvent(
    override val eventIdentifier: NavigationEventIdentifier,
    override val viewId: String?,
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