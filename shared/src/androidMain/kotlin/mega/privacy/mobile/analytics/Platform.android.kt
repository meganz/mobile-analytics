package mega.privacy.mobile.analytics

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val baseIdentifier = 300_000
}

actual fun getPlatform(): Platform = AndroidPlatform()