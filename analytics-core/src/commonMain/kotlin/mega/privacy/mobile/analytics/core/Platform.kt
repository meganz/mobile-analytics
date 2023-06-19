package mega.privacy.mobile.analytics.core

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
}

/**
 * Get platform
 *
 * @return current platform implementation
 */
expect fun getPlatform(): Platform