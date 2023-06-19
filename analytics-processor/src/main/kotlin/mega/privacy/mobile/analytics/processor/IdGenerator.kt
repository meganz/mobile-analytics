package mega.privacy.mobile.analytics.processor

import mega.privacy.mobile.analytics.processor.exception.NoIdAvailableException

/**
 * Id generator
 *
 * @property validRange
 */
class IdGenerator(private val validRange: IntRange) {
    /**
     * Invoke
     *
     * @param name
     * @param currentIdentifiers
     */
    operator fun invoke(name: String, currentIdentifiers: Map<String, Int>) =
        if (currentIdentifiers.containsKey(name)) currentIdentifiers else addIdentifier(
            name,
            currentIdentifiers
        )

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
