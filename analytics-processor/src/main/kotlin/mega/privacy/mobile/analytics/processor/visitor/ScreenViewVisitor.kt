package mega.privacy.mobile.analytics.processor.visitor

import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class ScreenViewVisitor(idGenerator: IdGenerator) :
    AnalyticsEventVisitor(idGenerator, ScreenViewEventIdentifier::class) {
}