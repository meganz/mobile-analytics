package mega.privacy.mobile.analytics.processor.generator

import mega.privacy.mobile.analytics.annotations.NotificationEvent
import mega.privacy.mobile.analytics.processor.generator.parameter.NotificationEventParameter
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.NotificationEventVisitor

internal class NotificationEventGeneratorTest : EventCodeGeneratorTest() {
    override val testParams = NotificationEventParameter()
    override val annotationClass = NotificationEvent::class

    override fun visitor(idGenerator: IdGenerator) = NotificationEventVisitor(
        idGenerator
    )
}