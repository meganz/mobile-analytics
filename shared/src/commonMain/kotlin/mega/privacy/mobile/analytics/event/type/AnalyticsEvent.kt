package mega.privacy.mobile.analytics.event.type

import mega.privacy.mobile.analytics.EventDataMapper
import mega.privacy.mobile.analytics.getPlatform
import mega.privacy.mobile.analytics.event.identifier.EventIdentifier

/**
 * Analytics event
 */
abstract class AnalyticsEvent {

    /**
     * Get event identifier
     *
     */
    fun getEventIdentifier() =
        platformEventIdentifier + eventTypeIdentifier + eventIdentifier.uniqueIdentifier

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