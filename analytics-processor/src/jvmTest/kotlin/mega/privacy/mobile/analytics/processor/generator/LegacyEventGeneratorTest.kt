package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.LegacyEventParameter
import mega.privacy.mobile.analytics.processor.generator.parameter.ScreenViewEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.LegacyEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor

internal class LegacyEventGeneratorTest : EventCodeGeneratorTest<GenerateLegacyIdRequest>() {
    override val testParams = LegacyEventParameter()
    override val annotationClass = LegacyEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateLegacyIdRequest>) = LegacyEventVisitor(
        idGenerator
    )
}