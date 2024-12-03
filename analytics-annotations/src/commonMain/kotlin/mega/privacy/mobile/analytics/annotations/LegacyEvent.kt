package mega.privacy.mobile.analytics.annotations


/**
 * Legacy event
 *
 * @property eventId
 */
@Target(AnnotationTarget.CLASS)
annotation class LegacyEvent(
    val eventId: Int
)