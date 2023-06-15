package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.ScreenViewEventIdentifier

/**
 * Screen view event
 *
 * @property eventIdentifier
 * @property viewId
 */
data class ScreenViewEvent(
    override val eventIdentifier: ScreenViewEventIdentifier,
    override val viewId: String,
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 0

    override val eventData: Map<String, Any?>
        get() {
            return emptyMap()
        }
}