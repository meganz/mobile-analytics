package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.TabSelectedEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor

internal class TabSelectedEventGeneratorTest : EventCodeGeneratorTest() {
    override val testParams = TabSelectedEventParameter()
    override val annotationClass = TabSelectedEvent::class

    override fun visitor(idGenerator: IdGenerator) = TabSelectedVisitor(
        idGenerator
    )
}