package mega.privacy.mobile.analytics.annotations

/**
 * Dialog displayed event
 *
 * @property dialog
 * @property screen
 */
@Target(AnnotationTarget.CLASS)
annotation class DialogDisplayedEvent(
    val dialog: String,
    val screen: String = "",
)
