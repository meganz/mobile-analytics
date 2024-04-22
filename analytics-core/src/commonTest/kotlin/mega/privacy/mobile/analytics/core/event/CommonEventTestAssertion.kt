package mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.type.AnalyticsEvent
import mega.privacy.mobile.analytics.core.getPlatform
import kotlin.test.assertEquals

internal const val eventIdentifier = 1
internal val expectedAppIdentifier = AppIdentifier(1)

fun testEventIdentifier(
    eventUnderTest: AnalyticsEvent,
    expected: Int,
) {
    val actual =
        eventUnderTest.getEventIdentifier() - getPlatform().baseIdentifier - eventIdentifier - expectedAppIdentifier.identifier
    assertEquals(expected = expected, actual = actual)
}