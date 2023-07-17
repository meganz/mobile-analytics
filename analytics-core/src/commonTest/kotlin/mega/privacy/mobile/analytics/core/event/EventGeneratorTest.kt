package mega.privacy.mobile.analytics.core.event

import kotlinx.coroutines.test.runTest
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.type.ButtonPressedEvent
import mega.privacy.mobile.analytics.core.event.type.DialogDisplayedEvent
import mega.privacy.mobile.analytics.core.event.type.GeneralEvent
import mega.privacy.mobile.analytics.core.event.type.ItemSelectedEvent
import mega.privacy.mobile.analytics.core.event.type.MenuItemEvent
import mega.privacy.mobile.analytics.core.event.type.NavigationEvent
import mega.privacy.mobile.analytics.core.event.type.NotificationEvent
import mega.privacy.mobile.analytics.core.event.type.ScreenViewEvent
import mega.privacy.mobile.analytics.core.event.type.TabSelectedEvent
import kotlin.test.Test
import kotlin.test.assertTrue

class EventGeneratorTest {
    private var currentViewId = "ViewId"

    private val fakeViewIdProvider = { currentViewId }

    private val underTest: EventGenerator = EventGenerator(viewIdProvider = fakeViewIdProvider)

    @Test
    fun `test that screen view event identifier returns a screen view event`() = runTest {
        val fakeScreenViewInfo = object : ScreenViewEventIdentifier {
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is ScreenViewEvent)
    }

    @Test
    internal fun `test that each screen view has a new view id`() = runTest {
        val fakeScreenViewInfo = object : ScreenViewEventIdentifier {
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        currentViewId = "1"
        val first = underTest.generateEvent(fakeScreenViewInfo).viewId
        currentViewId = "2"
        val second = underTest.generateEvent(fakeScreenViewInfo).viewId

        assertTrue(first != second)
    }

    @Test
    fun `test that button pressed event identifier returns a button pressed event`() = runTest {
        val fakeScreenViewInfo = object : ButtonPressedEventIdentifier {
            override val buttonName: String
                get() = "expected"
            override val screenName: String?
                get() = "expected"
            override val dialogName: String?
                get() = "expected"
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is ButtonPressedEvent)
    }

    @Test
    fun `test that dialog displayed event identifier returns a dialog displayed event`() = runTest {
        val fakeScreenViewInfo = object : DialogDisplayedEventIdentifier {
            override val screenName: String?
                get() = "expected"
            override val dialogName: String
                get() = "expected"
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is DialogDisplayedEvent)
    }

    @Test
    fun `test that general event identifier returns a general event`() = runTest {
        val fakeScreenViewInfo = object : GeneralEventIdentifier {
            override val info: Map<String, Any?>
                get() = emptyMap()
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is GeneralEvent)
    }

    @Test
    fun `test that item selected event identifier returns a item selected event`() = runTest {
        val fakeScreenViewInfo = object : ItemSelectedEventIdentifier {
            override val info: Map<String, Any>?
                get() = null
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is ItemSelectedEvent)
    }

    @Test
    fun `test that menu item event identifier returns a menu item event`() = runTest {
        val fakeScreenViewInfo = object : MenuItemEventIdentifier {
            override val menuItem: String
                get() = "expected"
            override val screenName: String?
                get() = "expected"
            override val menuType: String?
                get() = "expected"
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is MenuItemEvent)
    }

    @Test
    fun `test that navigation event identifier returns a navigation event`() = runTest {
        val fakeScreenViewInfo = object : NavigationEventIdentifier {
            override val navigationElementType: String?
                get() = "expected"
            override val destination: String?
                get() = "expected"
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is NavigationEvent)
    }

    @Test
    fun `test that notification event identifier returns a notification event`() = runTest {
        val fakeScreenViewInfo = object : NotificationEventIdentifier {
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is NotificationEvent)
    }

    @Test
    fun `test that tab selected event identifier returns a tab selected event`() = runTest {
        val fakeScreenViewInfo = object : TabSelectedEventIdentifier {
            override val screenName: String
                get() = "expected"
            override val tabName: String
                get() = "expected"
            override val eventName: String
                get() = "expectedName"
            override val uniqueIdentifier: Int
                get() = 123
        }
        val actual = underTest.generateEvent(fakeScreenViewInfo)

        assertTrue(actual is TabSelectedEvent)
    }
}