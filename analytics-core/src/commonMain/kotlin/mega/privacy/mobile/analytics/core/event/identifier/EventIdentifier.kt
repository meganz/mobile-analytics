package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Event identifier
 */
abstract class EventIdentifier {
    /**
     * Event name
     */
    abstract val eventName: String

    /**
     * Unique identifier
     */
    abstract val uniqueIdentifier: Int
}