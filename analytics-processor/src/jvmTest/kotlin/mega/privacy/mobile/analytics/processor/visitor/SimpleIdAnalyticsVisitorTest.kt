package mega.privacy.mobile.analytics.processor.visitor

import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import org.mockito.kotlin.KStubbing
import org.mockito.kotlin.any

abstract class SimpleIdAnalyticsVisitorTest<T : AnalyticsEventVisitor<GenerateSimpleIdRequest>> :
    AnalyticsVisitorTest<T, GenerateSimpleIdRequest>() {

    override fun stubGenerator(): KStubbing<IdGenerator<GenerateSimpleIdRequest>>.(IdGenerator<GenerateSimpleIdRequest>) -> Unit =
        {
            on { invoke(any()) }.thenAnswer { mapOf((it.arguments[0] as GenerateSimpleIdRequest).eventName to eventIdentifier) }
        }
}