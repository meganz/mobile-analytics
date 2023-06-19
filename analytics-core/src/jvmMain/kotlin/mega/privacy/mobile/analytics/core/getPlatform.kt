package mega.privacy.mobile.analytics.core

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
}