package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.GestureEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.GestureEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.GestureEventVisitor

internal class GestureEventGeneratorTest :
    EventCodeGeneratorTest<GenerateSimpleIdRequest>() {
    override val testParams = GestureEventParameter()
    override val annotationClass = GestureEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        GestureEventVisitor(
            idGenerator
        )
}