package mega.privacy.mobile.analytics.core.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * Json mapper
 */
class JsonMapper : EventDataMapper {
    override fun mapData(eventData: Map<String, Any?>) = runCatching {
        getMapJson(eventData).toString()
            .replace("\"", "\\\"")
    }.getOrDefault("")

    private fun getMapJson(map: Map<*, *>) = JsonObject(
        map.mapKeys { it.key.toString() }
            .mapValues {
                getJsonElement(it.value)
            }
    )

    private fun getJsonElement(value: Any?): JsonElement {
        return when (value) {
            null -> JsonNull
            is Int -> JsonPrimitive(value)
            is Boolean -> JsonPrimitive(value)
            is Long -> JsonPrimitive(value)
            is List<*> -> getListJson(value)
            is Map<*, *> -> getMapJson(value)
            else -> JsonPrimitive(value.toString())
        }
    }

    private fun getListJson(list: List<*>) = JsonArray(
        list.map {
            getJsonElement(it)
        }
    )
}