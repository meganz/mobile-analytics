package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.TabSelectedEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor

internal class TabSelectedEventGeneratorTest : EventCodeGeneratorTest<GenerateSimpleIdRequest>() {
    override val testParams = TabSelectedEventParameter()
    override val annotationClass = TabSelectedEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) = TabSelectedVisitor(
        idGenerator
    )
}