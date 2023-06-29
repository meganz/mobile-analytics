package mega.privacy.mobile.analytics.event.api

/**
 * Event sender
 */
interface EventSender {
    /**
     * Send event
     *
     * @param eventId
     * @param message
     * @param addJourneyId
     * @param viewId
     */
    fun sendEvent(
        eventId: Int,
        message: String,
        addJourneyId: Boolean,
        viewId: String?,
    )
}
