package mega.privacy.mobile.analytics.processor.identifier

import com.google.common.truth.Truth.assertThat
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.streams.asStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LegacyIdGeneratorTest {
    private val underTest = LegacyIdGenerator()

    @Test
    fun `test that map is unchanged if matching name and id found`() {
        val legacyId = 12345
        val eventName = "eventName"
        val currentIdMap = mapOf(eventName to legacyId)

        val request = GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = eventName,
            currentIdMap = currentIdMap,
        )

        val actual = underTest(request)

        assertThat(actual).isEqualTo(currentIdMap)
    }

    @Test
    fun `test that id is added if not found`() {
        val legacyId = 12345
        val eventName = "eventName"

        val request = GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = eventName,
            currentIdMap = emptyMap(),
        )

        val actual = underTest(request)

        assertThat(actual[eventName]).isEqualTo(legacyId)
    }

    @Test
    fun `test that an exception is thrown if name exists with a different identifier`() {
        val legacyId = 12345
        val eventName = "eventName"
        val currentIdMap = mapOf(eventName to 0)

        val request = GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = eventName,
            currentIdMap = currentIdMap,
        )

        assertThrows<IllegalStateException> { underTest(request) }
    }

    @Test
    fun `test that an exception is thrown if id exists with a different key`() {
        val legacyId = 12345
        val eventName = "eventName"
        val currentIdMap = mapOf("otherName" to legacyId)

        val request = GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = eventName,
            currentIdMap = currentIdMap,
        )

        assertThrows<IllegalStateException> { underTest(request) }
    }

    @ParameterizedTest
    @MethodSource(value = ["rangeSource"])
    fun `test that exception is thrown if id falls within 300 000 to 499 999`(legacyId: Int) {
        val eventName = "eventName"

        val request = GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = eventName,
            currentIdMap = emptyMap(),
        )

        assertThrows<IllegalStateException> { underTest(request) }
    }

    private fun rangeSource() = (300_000..499_999).step(1000).asSequence().asStream()
}