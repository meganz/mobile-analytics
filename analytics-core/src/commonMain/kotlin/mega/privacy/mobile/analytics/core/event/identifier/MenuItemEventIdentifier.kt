package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Menu item event identifier
 */
interface MenuItemEventIdentifier : EventIdentifier {
    /**
     * Menu item
     */
    val menuItem: String

    /**
     * Screen name
     */
    val screenName: String?

    /**
     * Menu type
     */
    val menuType: String?
}