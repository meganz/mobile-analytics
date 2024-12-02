package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier

/**
 * Legacy event
 *
 * @property viewId
 * @property appIdentifier
 * @property eventIdentifier
 */
class LegacyEvent(
    override val eventIdentifier: LegacyEventIdentifier,
    override val viewId: String?,
    override val appIdentifier: AppIdentifier,
) : AnalyticsEvent() {

    override fun getEventIdentifier() = eventIdentifier.uniqueIdentifier

    override val eventTypeIdentifier: Int
        get() = 0

    override val eventData: Map<String, Any?>
        get() = emptyMap()
}