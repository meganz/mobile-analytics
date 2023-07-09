package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Navigation event identifier
 */
abstract class NavigationEventIdentifier : EventIdentifier() {
    /**
     * Navigation element type
     */
    abstract val navigationElementType: String?

    /**
     * Destination
     */
    abstract val destination: String?
}