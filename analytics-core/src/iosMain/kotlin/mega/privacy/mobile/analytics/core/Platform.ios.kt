package mega.privacy.mobile.analytics.core

import platform.UIKit.UIDevice

/**
 * iOS platform
 */
class IOSPlatform : Platform {
    /**
     * Name
     */
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

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
actual fun getPlatform(): Platform = IOSPlatform()