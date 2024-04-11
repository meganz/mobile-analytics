package mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.identifier

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AppIdentifierTest {
    private lateinit var underTest: AppIdentifier

    @Test
    fun `test that app identifier throws an exception when id is more than 9`() {
        assertFailsWith<IllegalArgumentException> {
            underTest = AppIdentifier(10)
        }
    }

    @Test
    fun `test that app identifier throws an exception when id is less than 0`() {
        assertFailsWith<IllegalArgumentException> {
            underTest = AppIdentifier(-1)
        }
    }

    @Test
    fun `test that app identifier returns the correct identifier when in range`() {
        underTest = AppIdentifier(5)
        assertEquals(50_000, underTest.identifier)
    }

    @Test
    fun `test that app identifier returns the correct identifier when 0`() {
        underTest = AppIdentifier(0)
        assertEquals(0, underTest.identifier)
    }
}