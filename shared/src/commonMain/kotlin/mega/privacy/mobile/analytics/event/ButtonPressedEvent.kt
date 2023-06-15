package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.identifier.EventIdentifier


/**
 * Button pressed event
 *
 * @property identifier
 * @property viewId
 * @constructor Create empty Button pressed event
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