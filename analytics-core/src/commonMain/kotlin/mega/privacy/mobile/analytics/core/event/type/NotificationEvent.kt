package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier

/**
 * Notification event
 *
 * @property eventIdentifier
 * @property viewId
 */
data class NotificationEvent(
    override val eventIdentifier: NotificationEventIdentifier,
    override val appIdentifier: AppIdentifier
) : AnalyticsEvent() {
    override val viewId: String? = null
    override val eventTypeIdentifier = 6000

    override val eventData: Map<String, Any?>
        get() {
            return emptyMap()
        }
}