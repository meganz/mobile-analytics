package mega.privacy.mobile.analytics.core.api

interface EventSender {
    fun sendEvent(
        eventId: Int,
        message: String,
        addJourneyId: Boolean,
        viewId: String?,
    )
}
