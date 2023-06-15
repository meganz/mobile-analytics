package mega.privacy.mobile.analytics

/**
 * Event data mapper
 */
interface EventDataMapper {
    /**
     * Map data
     *
     * @param eventData
     * @return json string representation of the data
     */
    fun mapData(eventData: Map<String, Any?>): String
}