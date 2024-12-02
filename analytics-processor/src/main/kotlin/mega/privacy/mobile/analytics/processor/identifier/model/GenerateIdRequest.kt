package mega.privacy.mobile.analytics.processor.identifier.model

/**
 * Generate id request
 */
interface GenerateIdRequest {
    /**
     * Event name
     */
    val eventName: String

    /**
     * Current id map
     */
    val currentIdMap: Map<String, Int>
}