package mega.privacy.mobile.analytics.core.event.identifier

/**
 * Gesture event identifier
 */
interface GestureEventIdentifier : EventIdentifier {
    /**
     * Screen name
     */
    val screenName: String?

    /**
     * Gesture name
     */
    val gestureName: String
}