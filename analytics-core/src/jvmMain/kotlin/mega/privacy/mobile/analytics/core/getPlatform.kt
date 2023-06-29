package mega.privacy.mobile.analytics.core

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Get platform
 *
 * @return current platform implementation
 */
actual fun getPlatform(): Platform = object : Platform {
    override val name: String
        get() = "JVM platform is no-op"
    override val baseIdentifier: Int
        get() = 0
    override val backgroundDispatcher: CoroutineDispatcher
        get() = throw NotImplementedError("No-op")
}

