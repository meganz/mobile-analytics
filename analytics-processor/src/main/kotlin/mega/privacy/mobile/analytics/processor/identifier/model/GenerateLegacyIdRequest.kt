package mega.privacy.mobile.analytics.processor.identifier.model

/**
 * Generate legacy id request
 *
 * @property legacyId
 * @property eventName
 * @property currentIdMap
 */
class GenerateLegacyIdRequest(
    val legacyId: Int,
    override val eventName: String,
    override val currentIdMap: Map<String, Int>
) : GenerateIdRequest