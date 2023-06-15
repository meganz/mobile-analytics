package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.GeneralEventIdentifier

/**
 * General event
 *
 * @property eventIdentifier
 * @property viewId
 */
data class GeneralEvent(
    override val eventIdentifier: GeneralEventIdentifier,
    override val viewId: String?,
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 7000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "info" to eventIdentifier.info,
            )
        }
}