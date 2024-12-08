package mega.privacy.mobile.analytics.processor.identifier

import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest

/**
 * Legacy id generator
 */
class LegacyIdGenerator : IdGenerator<GenerateLegacyIdRequest> {

    override fun invoke(request: GenerateLegacyIdRequest): Map<String, Int> {
        val idMap = request.currentIdMap.toMutableMap()

        checkRestrictedRange(request)
        checkExistingIds(idMap, request)
        idMap.putIfAbsent(request.eventName, request.legacyId)
        return idMap
    }

    private fun checkRestrictedRange(request: GenerateLegacyIdRequest) {
        if ((300_000..499_999).contains(request.legacyId)) throw IllegalStateException("Id provided, ${request.legacyId}, is not allowed to fall within the range 300 000 to 499 999")
    }

    private fun checkExistingIds(
        idMap: MutableMap<String, Int>,
        request: GenerateLegacyIdRequest,
    ) {
        idMap.entries.find { it.key == request.eventName && it.value != request.legacyId }?.let {
            throw IllegalStateException(
                "Id for ${it.key} already exists, but id is ${it.value}, not ${request.legacyId}"
            )
        }

        idMap.entries.find { it.value == request.legacyId && it.key != request.eventName }?.let {
            throw IllegalStateException("Id ${it.value} already exists, but event name is ${it.key}, not ${request.eventName}")
        }
    }
}