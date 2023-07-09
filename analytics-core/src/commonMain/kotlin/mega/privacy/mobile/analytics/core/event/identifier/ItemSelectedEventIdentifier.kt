package mega.privacy.mobile.analytics.core.event.identifier

/**
 * General event identifier
 */
abstract class ItemSelectedEventIdentifier : EventIdentifier() {
    /**
     * Info
     */
    abstract val info: Map<String, Any>?
}