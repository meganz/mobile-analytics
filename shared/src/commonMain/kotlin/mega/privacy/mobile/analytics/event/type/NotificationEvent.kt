package mega.privacy.mobile.analytics.event.type

import mega.privacy.mobile.analytics.event.identifier.NotificationEventIdentifier

/**
 * Notification event
 *
 * @property eventIdentifier
 * @property viewId
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