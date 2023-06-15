package mega.privacy.mobile.analytics.identifier

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