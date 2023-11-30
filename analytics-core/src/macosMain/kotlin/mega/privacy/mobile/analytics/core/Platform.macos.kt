package mega.privacy.mobile.analytics.core


import platform.Foundation.NSProcessInfo

/**
 * macos platform
 */
class MacOSPlatform : Platform {
    /**
     * Name
     */
    override val name: String =
        NSProcessInfo.processInfo.operatingSystemName() + NSProcessInfo.processInfo.operatingSystemVersionString()


    /**
     * Base identifier
     */
    override val baseIdentifier = 400_000
}

/**
 * Get platform
 *
 * @return iOS Platform
 */
actual fun getPlatform(): Platform = MacOSPlatform()