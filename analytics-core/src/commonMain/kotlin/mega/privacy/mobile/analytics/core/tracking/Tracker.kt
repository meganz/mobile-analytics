package mega.privacy.mobile.analytics.core.tracking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.api.EventSender
import mega.privacy.mobile.analytics.core.api.ViewIdProvider
import mega.privacy.mobile.analytics.core.event.EventGenerator
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.getPlatform
import mega.privacy.mobile.analytics.core.mapper.JsonMapper

/**
 * Tracker
 *
 * @property eventSender
 * @constructor
 *
 * @param viewIdProvider
 */
class Tracker(
    viewIdProvider: ViewIdProvider,
    private val eventSender: EventSender,
) {
    private val eventGenerator = EventGenerator(viewIdProvider = viewIdProvider)
    private val background = getPlatform().backgroundDispatcher
    private val scope = CoroutineScope(SupervisorJob() + background)
    private val eventDataMapper = JsonMapper()

    /**
     * Track event
     *
     * @param eventIdentifier
     */
    fun trackEvent(eventIdentifier: EventIdentifier) {
        scope.launch {
            val event = eventGenerator.generateEvent(eventIdentifier)
            eventSender.sendEvent(
                eventId = event.getEventIdentifier(),
                message = event.getEventMessage(eventDataMapper),
                addJourneyId = true,
                viewId = event.viewId
            )
        }
    }
}