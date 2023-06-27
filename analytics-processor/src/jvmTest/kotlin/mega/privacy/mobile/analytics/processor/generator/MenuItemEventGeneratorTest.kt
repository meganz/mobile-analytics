package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.MenuItemEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.MenuItemEventVisitor

internal class MenuItemEventGeneratorTest : EventCodeGeneratorTest() {
    override val testParams = MenuItemEventParameter()
    override val annotationClass = MenuItemEvent::class

    override fun visitor(idGenerator: IdGenerator) = MenuItemEventVisitor(
        idGenerator
    )
}