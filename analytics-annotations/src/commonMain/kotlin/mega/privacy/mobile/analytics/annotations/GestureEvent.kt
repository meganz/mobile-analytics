package mega.privacy.mobile.analytics.annotations

/**
 * Gesture event
 *
 * @property gesture
 * @property screen
 */
@Target(AnnotationTarget.CLASS)
annotation class GestureEvent(
    val gesture: String,
    val screen: String = "",
)
