package mega.privacy.mobile.analytics.event.api

/**
 * View id provider
 */
interface ViewIdProvider {
    /**
     * Get view identifier
     *
     * @return
     */
    suspend fun getViewIdentifier(): String
}
