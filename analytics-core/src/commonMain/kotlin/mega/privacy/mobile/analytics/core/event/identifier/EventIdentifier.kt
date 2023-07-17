package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Event identifier
 */
interface EventIdentifier {
    /**
     * Event name
     */
    val eventName: String

    /**
     * Unique identifier
     */
    val uniqueIdentifier: Int
}