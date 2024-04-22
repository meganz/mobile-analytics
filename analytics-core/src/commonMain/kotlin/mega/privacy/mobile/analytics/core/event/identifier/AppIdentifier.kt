package mega.privacy.mobile.analytics.core.event.identifier

data class AppIdentifier(private val id: Int) {
    init {
        require(id in 0 .. 9) { "App identifier must be in 0 to 9" }
    }

    val identifier = id * 10_000
}