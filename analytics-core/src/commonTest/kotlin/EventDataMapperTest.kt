package mega.privacy.mobile.analytics.core.mapper

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class EventDataMapperTest() {
    private val underTest: EventDataMapper = JsonMapper()

    @Test
    fun `test that values are mapped correctly`(){
        val input = mapOf(
            "string" to "string",
            "int" to 1,
            "long" to 1L,
            "null" to null,
            "array" to listOf(1, 2, 3)
        )

        val actual = underTest.mapData(input)

        val expected = """{"string":"string","int":1,"long":1,"null":null,"array":[1,2,3]}"""
            .replace("\"", "\\\"")

        assertEquals(expected, actual)
    }

    @Test
    fun `test that mapData keeps a value with a double quote inside the string when embedded`() {
        val value = "a\"b"

        val actual = decodeAsEmbedded(underTest.mapData(mapOf("referrerUrl" to value)))

        assertEquals(value, actual["referrerUrl"]?.jsonPrimitive?.content)
    }

    @Test
    fun `test that mapData keeps a value with a backslash inside the string when embedded`() {
        val value = "a\\\"},\"x\":\"y"

        val actual = decodeAsEmbedded(underTest.mapData(mapOf("referrerUrl" to value)))

        assertEquals(setOf("referrerUrl"), actual.keys)
        assertEquals(value, actual["referrerUrl"]?.jsonPrimitive?.content)
    }

    /**
     * Decodes [mapped] the same way the server does. The SDK writes the mapped data, without
     * escaping, as the value of the "m" JSON string ("m":"<mapped data>").
     */
    private fun decodeAsEmbedded(mapped: String): JsonObject {
        val command = Json.parseToJsonElement("""{"a":"log","m":"$mapped"}""").jsonObject
        val message = command.getValue("m").jsonPrimitive.content
        return Json.parseToJsonElement(message).jsonObject
    }
}
