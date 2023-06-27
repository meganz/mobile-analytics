package mega.privacy.mobile.analytics.annotations

/**
 * Button press event
 *
 * @property buttonName
 * @property screen
 * @property dialog
 */
@Target(AnnotationTarget.CLASS)
annotation class ButtonPressEvent(
    val buttonName: String,
    val screen: String = "",
    val dialog: String = "",
)
