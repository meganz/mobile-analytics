package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.NotificationEventIdentifier

/**
 * Notification event
 *
 * @property identifier
 * @property viewId
 * @constructor Create empty Notification event
 */
data class NotificationEvent(
    override val eventIdentifier: NotificationEventIdentifier,
) : AnalyticsEvent() {
    override val viewId: String? = null
    override val eventTypeIdentifier = 6000

    override val eventData: Map<String, Any?>
        get() {
            return emptyMap()
        }
}