package mega.privacy.mobile.analytics.core.event.type

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.mapper.EventDataMapper
import mega.privacy.mobile.analytics.core.getPlatform
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier

/**
 * Analytics event
 */
abstract class AnalyticsEvent {

    /**
     * Get event identifier
     *
     */
    fun getEventIdentifier() =
        platformEventIdentifier + eventTypeIdentifier + eventIdentifier.uniqueIdentifier + appIdentifier.identifier

    /**
     * Get event message
     *
     * @param mapper
     * @return event message string in the format "<Event name> <Event Data Json>"
     */
    fun getEventMessage(mapper: EventDataMapper): String {
        return "${eventIdentifier.eventName} ${mapper.mapData(eventData)}"
    }

    /**
     * View id
     */
    abstract val viewId: String?

    /**
     * App identifier
     */
    abstract val appIdentifier: AppIdentifier

    /**
     * Identifier
     */
    protected abstract val eventIdentifier: EventIdentifier

    /**
     * Event type identifier
     */
    protected abstract val eventTypeIdentifier: Int

    /**
     * Data
     *
     * @return
     */
    protected abstract val eventData: Map<String, Any?>

    private val platformEventIdentifier = getPlatform().baseIdentifier
}