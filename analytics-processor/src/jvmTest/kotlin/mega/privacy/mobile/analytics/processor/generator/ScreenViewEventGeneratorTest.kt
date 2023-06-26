package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.ScreenViewEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor

internal class ScreenViewEventGeneratorTest : EventCodeGeneratorTest() {
    override val testParams = ScreenViewEventParameter()
    override val annotationClass = ScreenViewEvent::class

    override fun visitor(idGenerator: IdGenerator) = ScreenViewVisitor(
        idGenerator
    )
}