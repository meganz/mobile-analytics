package mega.privacy.mobile.analytics.core.api

interface ViewIdProvider {
    suspend fun getViewIdentifier(): String
}
