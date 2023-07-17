package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Navigation event identifier
 */
interface NavigationEventIdentifier : EventIdentifier {
    /**
     * Navigation element type
     */
    val navigationElementType: String?

    /**
     * Destination
     */
    val destination: String?
}