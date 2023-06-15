package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.DialogDisplayedEventIdentifier

/**
 * Dialog displayed event
 *
 * @property eventIdentifier
 * @property viewId
 */
class DialogDisplayedEvent(
    override val eventIdentifier: DialogDisplayedEventIdentifier,
    override val viewId: String?,
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 3000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "screen_name" to eventIdentifier.screenName,
                "dialog_name" to eventIdentifier.dialogName
            )
        }
}