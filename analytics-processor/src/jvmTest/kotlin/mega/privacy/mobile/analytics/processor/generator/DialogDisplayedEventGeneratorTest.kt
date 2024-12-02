package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.DialogDisplayedEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.DialogDisplayedEventVisitor

internal class DialogDisplayedEventGeneratorTest :
    EventCodeGeneratorTest<GenerateSimpleIdRequest>() {
    override val testParams = DialogDisplayedEventParameter()
    override val annotationClass = DialogDisplayedEvent::class

    override fun visitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) =
        DialogDisplayedEventVisitor(
            idGenerator
        )
}