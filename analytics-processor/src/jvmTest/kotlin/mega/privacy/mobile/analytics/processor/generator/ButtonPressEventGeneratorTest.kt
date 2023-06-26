package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.ButtonPressEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.ButtonPressVisitor

internal class ButtonPressEventGeneratorTest : EventCodeGeneratorTest() {
    override val testParams = ButtonPressEventParameter()
    override val annotationClass = ButtonPressEvent::class

    override fun visitor(idGenerator: IdGenerator) = ButtonPressVisitor(
        idGenerator
    )
}