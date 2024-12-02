package mega.privacy.mobile.analytics.core.event

import kotlinx.coroutines.test.runTest
import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.type.ButtonPressedEvent
import mega.privacy.mobile.analytics.core.event.type.DialogDisplayedEvent
import mega.privacy.mobile.analytics.core.event.type.GeneralEvent
import mega.privacy.mobile.analytics.core.event.type.ItemSelectedEvent
import mega.privacy.mobile.analytics.core.event.type.LegacyEvent
import mega.privacy.mobile.analytics.core.event.type.MenuItemEvent
import mega.privacy.mobile.analytics.core.event.type.NavigationEvent
import mega.privacy.mobile.analytics.core.event.type.NotificationEvent
import mega.privacy.mobile.analytics.core.event.type.ScreenViewEvent
import mega.privacy.mobile.analytics.core.event.type.TabSelectedEvent
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventDataProvider
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.FakeEventProvider
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EventGeneratorTest {
    private var currentViewId = "ViewId"

    private val fakeViewIdProvider = { currentViewId }
    private val fakeAppIdentifier = AppIdentifier(0)
    private val underTest: EventGenerator =
        EventGenerator(viewIdProvider = fakeViewIdProvider, appIdentifier = fakeAppIdentifier)
    private val eventIdentifierProvider = EventDataProvider.eventIdentifierProvider

    @Test
    fun `test that screen view event identifier returns a screen view event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.screenViewEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)
        assertTrue(actual is ScreenViewEvent)
    }

    @Test
    internal fun `test that each screen view has a new view id`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.screenViewEventIdentifier
        currentViewId = "1"
        val first = underTest.generateEvent(fakeScreenViewInfo).viewId
        currentViewId = "2"
        val second = underTest.generateEvent(fakeScreenViewInfo).viewId

        assertTrue(first != second)
    }

    @Test
    fun `test that button pressed event identifier returns a button pressed event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.buttonPressedEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is ButtonPressedEvent)
    }

    @Test
    fun `test that dialog displayed event identifier returns a dialog displayed event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.dialogDisplayedEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is DialogDisplayedEvent)
    }

    @Test
    fun `test that general event identifier returns a general event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.generalEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is GeneralEvent)
    }

    @Test
    fun `test that item selected event identifier returns a item selected event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.itemSelectedEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is ItemSelectedEvent)
    }

    @Test
    fun `test that menu item event identifier returns a menu item event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.menuItemEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is MenuItemEvent)
    }

    @Test
    fun `test that navigation event identifier returns a navigation event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.navigationEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is NavigationEvent)
    }

    @Test
    fun `test that notification event identifier returns a notification event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.notificationEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is NotificationEvent)
    }

    @Test
    fun `test that tab selected event identifier returns a tab selected event`() = runTest {
        val fakeScreenViewInfo = FakeEventProvider.tabSelectedEventIdentifier
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is TabSelectedEvent)
    }

    @Test
    fun `test that legacy event identifier returns a legacy event`() = runTest {
        val fakeLegacyEventInfo = FakeEventProvider.legacyEventIdentifier
        val actual = underTest.generateEvent(fakeLegacyEventInfo)

        assertTrue(actual is LegacyEvent)
    }

    @Test
    internal fun `test that every event returns with the correct app identifier`() = runTest {
        eventIdentifierProvider.forEach {
            val actual = underTest.generateEvent(it).appIdentifier
            assertEquals(actual, fakeAppIdentifier)
        }
    }
}