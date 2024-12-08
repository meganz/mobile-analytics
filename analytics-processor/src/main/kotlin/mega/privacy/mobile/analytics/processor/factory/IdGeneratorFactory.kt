package mega.privacy.mobile.analytics.processor.factory

import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.LegacyIdGenerator
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateIdRequest
import mega.privacy.mobile.analytics.processor.visitor.AnalyticsEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.LegacyEventVisitor
import kotlin.reflect.KClass

/**
 * Id generator factory
 */
class IdGeneratorFactory {

    /**
     * Invoke
     *
     * @param visitorClass
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <T : GenerateIdRequest> invoke(visitorClass: KClass<out AnalyticsEventVisitor<T>>): IdGenerator<T> =
        (when (visitorClass) {
            LegacyEventVisitor::class -> LegacyIdGenerator()
            else -> SingleRangeIdGenerator(
                0..999
            )
        }) as IdGenerator<T>
}