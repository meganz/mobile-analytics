package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Button pressed event identifier
 */
abstract class ButtonPressedEventIdentifier : EventIdentifier() {
    /**
     * Button name
     */
    abstract val buttonName: String

    /**
     * Screen name
     */
    abstract val screenName: String?

    /**
     * Dialog name
     */
    abstract val dialogName: String?
}