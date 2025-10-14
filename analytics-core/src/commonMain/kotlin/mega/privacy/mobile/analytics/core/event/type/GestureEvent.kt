package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier

/**
 * Gesture displayed event
 *
 * @property eventIdentifier
 * @property viewId
 */
class GestureEvent(
    override val eventIdentifier: GestureEventIdentifier,
    override val viewId: String?,
    override val appIdentifier: AppIdentifier
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 9000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "screen_name" to eventIdentifier.screenName,
                "gesture_name" to eventIdentifier.gestureName,
            )
        }
}