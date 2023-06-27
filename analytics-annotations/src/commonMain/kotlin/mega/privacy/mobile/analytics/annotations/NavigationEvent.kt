package mega.privacy.mobile.analytics.annotations

/**
 * Navigation event
 *
 * @property destination
 * @property navigationElementType
 */
@Target(AnnotationTarget.CLASS)
annotation class NavigationEvent(
    val destination: String,
    val navigationElementType: NavigationElementType,
) {
    /**
     * Navigation element type
     */
    enum class NavigationElementType {
        /**
         * Bottom
         */
        Bottom,

        /**
         * Drawer
         */
        Drawer,

        /**
         * Toolbar
         */
        Toolbar,

        /**
         * System
         */
        System,

        /**
         * Other
         */
        Other
    }
}
