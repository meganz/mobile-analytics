import com.google.common.truth.Truth.assertThat
import mega.privacy.mobile.analytics.processor.IdGenerator
import mega.privacy.mobile.analytics.processor.exception.NoIdAvailableException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IdGeneratorTest {
    private lateinit var underTest: IdGenerator

    private val idRange = 8..50

    @BeforeEach
    internal fun setUp() {
        underTest = IdGenerator(idRange)
    }

    @Test
    internal fun `test that id is returned if found in list`() {
        val testName = "TestName"
        val expected = 23
        val idMap = mapOf(testName to expected)
        val actual = underTest(testName, idMap)[testName]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `test that first number in range is added as id if not present in list`() {
        val testName = "TestName"
        val expected = idRange.first
        val idMap = emptyMap<String, Int>()
        val actual = underTest(testName, idMap)[testName]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `test that next number in range is added if first is already used`() {
        val testName = "TestName"
        val expected = idRange[1]
        val idMap = mapOf("Existing" to idRange[0])
        val actual = underTest(testName, idMap)[testName]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `test that existing ids in the map are skipped`() {
        val testName = "TestName"
        val expected = idRange[2]
        val idMap = mapOf(
            "Existing" to idRange[0],
            "Existing2" to idRange[1]
        )
        val actual = underTest(testName, idMap)[testName]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    internal fun `test that an exception is thrown if no valid id exists in range`() {
        val testName = "TestName"
        val idMap = idRange.associateBy { it.toString() }
        assertThrows<NoIdAvailableException> { underTest(testName, idMap) }
    }

    operator fun IntRange.get(index: Int) = this.toList()[index]
}