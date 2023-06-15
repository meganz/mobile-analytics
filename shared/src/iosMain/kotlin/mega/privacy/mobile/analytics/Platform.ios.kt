package mega.privacy.mobile.analytics

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val baseIdentifier = 400_000
}

actual fun getPlatform(): Platform = IOSPlatform()