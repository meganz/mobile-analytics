package mega.privacy.mobile.analytics.event.identifier

/**
 * Tab selected event identifier
 */
interface TabSelectedEventIdentifier : EventIdentifier {
    /**
     * Screen name
     */
    val screenName: String

    /**
     * Tab name
     */
    val tabName: String
}