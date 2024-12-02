package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.GeneralEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper

internal class GeneralEventCodeGeneratorTest : EventCodeGeneratorTest<GenerateSimpleIdRequest>() {

    override val testParams = GeneralEventParameter()
    override val annotationClass = GeneralEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) = GeneralEventVisitor(
        ConstructorParameterMapper(),
        idGenerator
    )

}



