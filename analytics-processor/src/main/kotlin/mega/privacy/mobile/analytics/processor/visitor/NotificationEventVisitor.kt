package mega.privacy.mobile.analytics.processor.visitor

import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class NotificationEventVisitor(idGenerator: IdGenerator) :
    AnalyticsEventVisitor(idGenerator, NotificationEventIdentifier::class)