package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.ItemSelectedEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.ItemSelectedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper

internal class ItemSelectedEventCodeGeneratorTest :
    EventCodeGeneratorTest<GenerateSimpleIdRequest>() {

    override val testParams = ItemSelectedEventParameter()
    override val annotationClass = ItemSelectedEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        ItemSelectedEventVisitor(
            ConstructorParameterMapper(),
            idGenerator
        )

}



