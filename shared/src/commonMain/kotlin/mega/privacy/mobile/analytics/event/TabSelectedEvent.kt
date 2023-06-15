package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.TabSelectedEventIdentifier

/**
 * Tab selected event
 *
 * @property eventIdentifier
 * @property viewId
 */
data class TabSelectedEvent(
    override val eventIdentifier: TabSelectedEventIdentifier,
    override val viewId: String,
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 1000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "screen_name" to eventIdentifier.screenName,
                "tab_name" to eventIdentifier.tabName
            )
        }
}