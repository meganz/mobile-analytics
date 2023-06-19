package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier


/**
 * Button pressed event
 *
 * @property eventIdentifier
 * @property viewId
 */
class ButtonPressedEvent(
    override val eventIdentifier: ButtonPressedEventIdentifier,
    override val viewId: String?,
) : AnalyticsEvent() {
    override val eventTypeIdentifier = 2000

    override val eventData: Map<String, Any?>
        get() {
            return mapOf(
                "screen_name" to eventIdentifier.screenName,
                "dialog_name" to eventIdentifier.dialogName,
                "button_name" to eventIdentifier.buttonName,
            )
        }
}