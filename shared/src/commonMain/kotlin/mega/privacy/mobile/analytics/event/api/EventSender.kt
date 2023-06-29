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
     * @param viewId
     */
    fun sendEvent(
        eventId: Int,
        message: String,
        viewId: String?,
    )
}
