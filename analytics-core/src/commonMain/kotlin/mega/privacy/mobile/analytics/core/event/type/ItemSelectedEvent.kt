package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier

/**
 * General event
 *
 * @property eventIdentifier
 * @property viewId
 */
data class ItemSelectedEvent(
    override val eventIdentifier: ItemSelectedEventIdentifier,
    override val viewId: String?,
    override val appIdentifier: AppIdentifier
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 8000

    override val eventData: Map<String, Any?>
        get() {
            return eventIdentifier.info
        }
}