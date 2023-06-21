package mega.privacy.mobile.analytics.processor.identifier

/**
 * Id generator
 */
interface IdGenerator {
    /**
     * Invoke
     *
     * @param name
     * @param currentIdentifiers
     */
    operator fun invoke(name: String, currentIdentifiers: Map<String, Int>): Map<String, Int>
}