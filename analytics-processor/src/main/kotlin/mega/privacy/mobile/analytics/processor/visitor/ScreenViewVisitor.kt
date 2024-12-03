package mega.privacy.mobile.analytics.processor.visitor

import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class ScreenViewVisitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) :
    SimpleIdAnalyticsEventVisitor(idGenerator, ScreenViewEventIdentifier::class)