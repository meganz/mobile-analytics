package mega.privacy.mobile.analytics.annotations

/**
 * Menu item event
 *
 * @property menuItem
 * @property menuType
 * @property screen
 */
@Target(AnnotationTarget.CLASS)
annotation class MenuItemEvent(
    val menuItem: String,
    val menuType: MenuType,
    val screen: String = "",
) {
    /**
     * Menu type
     */
    enum class MenuType {
        /**
         * Toolbar
         */
        Toolbar,

        /**
         * Item
         */
        Item
    }
}
