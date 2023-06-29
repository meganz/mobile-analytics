package mega.privacy.mobile.analytics.core.mapper

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
}