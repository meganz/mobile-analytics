package mega.privacy.mobile.analytics.processor.identifier

import mega.privacy.mobile.analytics.processor.exception.NoIdAvailableException
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest

/**
 * Id generator
 *
 * @property validRange
 */
internal class SingleRangeIdGenerator(private val validRange: IntRange) :
    IdGenerator<GenerateSimpleIdRequest> {
    /**
     * Invoke
     *
     * @param request
     */
    override operator fun invoke(request: GenerateSimpleIdRequest): Map<String, Int> {
        val name = request.eventName
        val currentIdentifiers = request.currentIdMap
        return if (currentIdentifiers.containsKey(name)) currentIdentifiers else addIdentifier(
            name,
            currentIdentifiers
        )
    }

    private fun addIdentifier(
        name: String,
        currentIdentifiers: Map<String, Int>,
    ) = try {
        val newMap = currentIdentifiers.toMutableMap()
        newMap[name] = validRange.first { !currentIdentifiers.values.contains(it) }
        newMap
    } catch (e: NoSuchElementException) {
        throw NoIdAvailableException(name)
    }
}