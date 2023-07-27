package mega.privacy.mobile.analytics.core.event.identifier

/**
 * General event identifier
 */
interface ItemSelectedEventIdentifier : EventIdentifier {
    /**
     * Info
     */
    val info: Map<String, Any?>
}