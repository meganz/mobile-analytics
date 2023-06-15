package mega.privacy.mobile.analytics.event.identifier

/**
 * Button pressed event identifier
 */
interface ButtonPressedEventIdentifier : EventIdentifier {
    /**
     * Button name
     */
    val buttonName: String

    /**
     * Screen name
     */
    val screenName: String?

    /**
     * Dialog name
     */
    val dialogName: String?
}