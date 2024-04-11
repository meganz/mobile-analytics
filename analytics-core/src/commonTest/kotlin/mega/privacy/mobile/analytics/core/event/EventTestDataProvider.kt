package mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event

import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.ButtonPressed
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.DialogDisplayed
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.General
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.ItemSelected
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.MenuItem
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.Navigation
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.Notification
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.ScreenView
import mega.privacy.mobile.analytics.core.mapper.mega.privacy.mobile.analytics.core.event.EventType.TabSelected

internal enum class EventType {
    ScreenView,
    ButtonPressed,
    DialogDisplayed,
    General,
    ItemSelected,
    MenuItem,
    Navigation,
    Notification,
    TabSelected
}

internal object FakeEventProvider {
    val screenViewEventIdentifier = object : ScreenViewEventIdentifier {
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val buttonPressedEventIdentifier = object : ButtonPressedEventIdentifier {
        override val buttonName: String
            get() = "expectedButtonName"
        override val screenName: String?
            get() = "expectedScreenName"
        override val dialogName: String?
            get() = "expectedDialogName"
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val dialogDisplayedEventIdentifier = object : DialogDisplayedEventIdentifier {
        override val screenName: String?
            get() = "expectedScreenName"
        override val dialogName: String
            get() = "expectedDialogName"
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val generalEventIdentifier = object : GeneralEventIdentifier {
        override val info: Map<String, Any?>
            get() = emptyMap()
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val itemSelectedEventIdentifier = object : ItemSelectedEventIdentifier {
        override val info: Map<String, Any?>
            get() = emptyMap()
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val menuItemEventIdentifier = object : MenuItemEventIdentifier {
        override val menuItem: String
            get() = "expectedMenuItem"
        override val screenName: String?
            get() = "expectedScreenName"
        override val menuType: String?
            get() = "expectedMenuType"
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val navigationEventIdentifier = object : NavigationEventIdentifier {
        override val navigationElementType: String?
            get() = "expectedNavigationElementType"
        override val destination: String?
            get() = "expectedDestination"
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val notificationEventIdentifier = object : NotificationEventIdentifier {
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
    val tabSelectedEventIdentifier = object : TabSelectedEventIdentifier {
        override val screenName: String
            get() = "expectedScreenName"
        override val tabName: String
            get() = "expectedTabName"
        override val eventName: String
            get() = "expectedEventName"
        override val uniqueIdentifier: Int
            get() = 123
    }
}

internal object EventDataProvider {
    val eventIdentifierProvider: Map<EventType, EventIdentifier> = mapOf(
        ScreenView to FakeEventProvider.screenViewEventIdentifier,
        ButtonPressed to FakeEventProvider.buttonPressedEventIdentifier,
        DialogDisplayed to FakeEventProvider.dialogDisplayedEventIdentifier,
        General to FakeEventProvider.generalEventIdentifier,
        ItemSelected to FakeEventProvider.itemSelectedEventIdentifier,
        MenuItem to FakeEventProvider.menuItemEventIdentifier,
        Navigation to FakeEventProvider.navigationEventIdentifier,
        Notification to FakeEventProvider.notificationEventIdentifier,
        TabSelected to FakeEventProvider.tabSelectedEventIdentifier
    )
}