package mega.privacy.mobile.analytics.core

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Platform
 */
interface Platform {
    /**
     * Name
     */
    val name: String

    /**
     * Base identifier
     */
    val baseIdentifier: Int

    /**
     * Background dispatcher
     */
    val backgroundDispatcher: CoroutineDispatcher
}

/**
 * Get platform
 *
 * @return current platform implementation
 */
expect fun getPlatform(): Platform