package mega.privacy.mobile.analytics.processor.identifier.model

/**
 * Generate simple id request
 *
 * @property eventName
 * @property currentIdMap
 */
class GenerateSimpleIdRequest(
    override val eventName: String,
    override val currentIdMap: Map<String, Int>
) : GenerateIdRequest