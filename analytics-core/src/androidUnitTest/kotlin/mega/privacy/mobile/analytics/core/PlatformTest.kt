package mega.privacy.mobile.analytics.core

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformTest {

    @Test
    fun `test that android platform base identifier is 300 000`() {
        assertEquals(300_000, getPlatform().baseIdentifier)
    }
}