package mega.privacy.mobile.analytics.processor.visitor

import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class NotificationEventVisitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) :
    SimpleIdAnalyticsEventVisitor(idGenerator, NotificationEventIdentifier::class)