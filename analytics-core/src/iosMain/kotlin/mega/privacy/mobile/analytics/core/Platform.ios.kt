package mega.privacy.mobile.analytics.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Runnable
import platform.UIKit.UIDevice
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext

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
    override val backgroundDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}

/**
 * Get platform
 *
 * @return iOS Platform
 */
actual fun getPlatform(): Platform = IOSPlatform()