package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Dialog displayed event identifier
 */
interface DialogDisplayedEventIdentifier : EventIdentifier {
    /**
     * Screen name
     */
    val screenName: String?

    /**
     * Dialog name
     */
    val dialogName: String
}