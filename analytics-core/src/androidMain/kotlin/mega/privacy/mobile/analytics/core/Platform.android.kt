package mega.privacy.mobile.analytics.core

/**
 * Android platform
 */
class AndroidPlatform : Platform {
    /**
     * Name
     */
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    /**
     * Base identifier
     */
    override val baseIdentifier = 300_000
}

/**
 * Get platform
 *
 * @return Android platform
 */
actual fun getPlatform(): Platform = AndroidPlatform()