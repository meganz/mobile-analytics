package mega.privacy.mobile.analytics.processor.identifier

import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest

/**
 * Legacy id generator
 */
class LegacyIdGenerator : IdGenerator<GenerateLegacyIdRequest> {

    override fun invoke(request: GenerateLegacyIdRequest): Map<String, Int> {
        val idMap = request.currentIdMap.toMutableMap()
        checkExistingIds(idMap, request)
        idMap.putIfAbsent(request.eventName, request.legacyId)
        return idMap
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