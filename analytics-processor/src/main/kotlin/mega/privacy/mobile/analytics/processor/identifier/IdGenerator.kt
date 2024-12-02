package mega.privacy.mobile.analytics.processor.identifier

import mega.privacy.mobile.analytics.processor.identifier.model.GenerateIdRequest

/**
 * Id generator
 */
interface IdGenerator<REQUEST : GenerateIdRequest> {
    /**
     * Invoke
     *
     * @param request
     */
    operator fun invoke(request: REQUEST): Map<String, Int>
}