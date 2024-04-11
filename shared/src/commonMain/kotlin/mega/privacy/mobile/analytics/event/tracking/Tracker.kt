package mega.privacy.mobile.analytics.event.tracking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mega.privacy.mobile.analytics.core.event.EventGenerator
import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.mapper.JsonMapper
import mega.privacy.mobile.analytics.event.api.EventSender
import mega.privacy.mobile.analytics.event.api.ViewIdProvider

/**
 * Tracker
 *
 * @property eventSender
 *
 * @param viewIdProvider
 */
class Tracker(
    viewIdProvider: ViewIdProvider,
    appIdentifier: AppIdentifier,
    private val eventSender: EventSender,
) {
    private val eventGenerator = EventGenerator(
        viewIdProvider = viewIdProvider::getViewIdentifier,
        appIdentifier = appIdentifier
    )
    private val background = Dispatchers.IO
    private val scope = CoroutineScope(SupervisorJob())
    private val eventDataMapper = JsonMapper()

    /**
     * Track event
     *
     * @param eventIdentifier
     */
    fun trackEvent(eventIdentifier: EventIdentifier) {
        scope.launch(background) {
            val event = eventGenerator.generateEvent(eventIdentifier)
            eventSender.sendEvent(
                eventId = event.getEventIdentifier(),
                message = event.getEventMessage(eventDataMapper),
                viewId = event.viewId
            )
        }
    }
}