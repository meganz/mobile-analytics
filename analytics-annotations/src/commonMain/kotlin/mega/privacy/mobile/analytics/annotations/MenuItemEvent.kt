package mega.privacy.mobile.analytics.annotations

/**
 * Menu item event
 */
@Target(AnnotationTarget.CLASS)
annotation class MenuItemEvent(
    val menuItem: String,
    val menuType: MenuType,
    val screen: String = "",
) {
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
