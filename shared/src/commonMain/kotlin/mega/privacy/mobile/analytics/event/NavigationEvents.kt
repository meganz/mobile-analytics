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

@NavigationEvent(
    destination = "Add From Contacts",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface InviteToMEGAAddFromContacts

@NavigationEvent(
    destination = "Enter Email Address",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface InviteToMEGAEnterEmailAddress

@NavigationEvent(
    destination = "Scan Code",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface InviteToMEGAScanCode

@NavigationEvent(
    destination = "Share Invite",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface InviteToMEGAShareInvite

@NavigationEvent(
    destination = "Sync List",
    navigationElementType = NavigationEvent.NavigationElementType.Toolbar
)
interface SyncNewFolderScreenBackNavigation

@NavigationEvent(
    destination = "Menu",
    navigationElementType = NavigationEvent.NavigationElementType.Bottom
)
interface MenuBottomNavigationItem