package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.NavigationEvent

@NavigationEvent(
    destination = "Android Sync",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface AndroidSyncNavigationItem

@NavigationEvent(
    destination = "Cloud Drive",
    navigationElementType = NavigationEvent.NavigationElementType.Bottom
)
interface CloudDriveBottomNavigationItem

@NavigationEvent(
    destination = "Chat rooms",
    navigationElementType = NavigationEvent.NavigationElementType.Bottom
)
interface ChatRoomsBottomNavigationItem

@NavigationEvent(
    destination = "My Account Profile",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyAccountProfileNavigationItem