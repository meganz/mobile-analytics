package mega.privacy.mobile.analytics

interface Platform {
    val name: String
    val baseIdentifier: Int
}

expect fun getPlatform(): Platform