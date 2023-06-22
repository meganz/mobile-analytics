package mega.privacy.mobile.analytics.annotations

/**
 * Tab selected event
 *
 * @property screenName
 * @property tabName
 */
@Target(AnnotationTarget.CLASS)
annotation class TabSelectedEvent(val screenName: String, val tabName: String)
