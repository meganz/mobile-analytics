package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Dialog displayed event identifier
 */
abstract  class DialogDisplayedEventIdentifier : EventIdentifier() {
    /**
     * Screen name
     */
    abstract val screenName: String?

    /**
     * Dialog name
     */
    abstract val dialogName: String
}