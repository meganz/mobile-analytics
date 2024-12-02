package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.NavigationEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.NavigationEventVisitor

internal class NavigationEventGeneratorTest : EventCodeGeneratorTest<GenerateSimpleIdRequest>() {
    override val testParams = NavigationEventParameter()
    override val annotationClass = NavigationEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        NavigationEventVisitor(
            idGenerator
        )
}