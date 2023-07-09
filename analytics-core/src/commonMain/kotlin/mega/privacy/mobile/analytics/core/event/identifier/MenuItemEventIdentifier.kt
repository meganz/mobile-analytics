package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Menu item event identifier
 */
abstract class MenuItemEventIdentifier : EventIdentifier() {
    /**
     * Menu item
     */
    abstract val menuItem: String

    /**
     * Screen name
     */
    abstract val screenName: String?

    /**
     * Menu type
     */
    abstract val menuType: String?
}