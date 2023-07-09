package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Tab selected event identifier
 */
abstract class TabSelectedEventIdentifier : EventIdentifier() {
    /**
     * Screen name
     */
    abstract val screenName: String

    /**
     * Tab name
     */
    abstract val tabName: String
}