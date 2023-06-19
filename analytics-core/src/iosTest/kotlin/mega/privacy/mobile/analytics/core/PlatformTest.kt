package mega.privacy.mobile.analytics.core

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformTest {

    @Test
    fun `test that ios platform base identifier is 400 000`() {
        assertEquals(400_000, getPlatform().baseIdentifier)
    }
}