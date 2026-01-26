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

@NavigationEvent(
    destination = "My Menu Storage",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuStorageNavigationItem

@NavigationEvent(
    destination = "My Menu Contacts",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuContactsNavigationItem

@NavigationEvent(
    destination = "My Menu Achievements",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuAchievementsNavigationItem

@NavigationEvent(
    destination = "My Menu SharedItems",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuSharedItemsNavigationItem

@NavigationEvent(
    destination = "My Menu Device Centre",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuDeviceCentreNavigationItem

@NavigationEvent(
    destination = "My Menu Transfers",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuTransfersNavigationItem

@NavigationEvent(
    destination = "My Menu Offline Files",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuOfflineFilesNavigationItem

@NavigationEvent(
    destination = "My Menu Rubbish Bin",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuRubbishBinNavigationItem

@NavigationEvent(
    destination = "My Menu Settings",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuSettingsNavigationItem

@NavigationEvent(
    destination = "My Menu MEGA VPN",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuMEGAVPNNavigationItem

@NavigationEvent(
    destination = "My Menu MEGA Pass",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuMEGAPassNavigationItem

@NavigationEvent(
    destination = "My Menu Transfer.it",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuTransferITNavigationItem

@NavigationEvent(
    destination = "Home",
    navigationElementType = NavigationEvent.NavigationElementType.Bottom
)
interface HomeBottomNavigationItem

@NavigationEvent(
    destination = "Photos",
    navigationElementType = NavigationEvent.NavigationElementType.Bottom
)
interface PhotosBottomNavigationItem

@NavigationEvent(
    destination = "Home",
    navigationElementType = NavigationEvent.NavigationElementType.Toolbar
)
interface VideosScreenBackNavigation

@NavigationEvent(
    destination = "My Menu Chat",
    navigationElementType = NavigationEvent.NavigationElementType.Drawer
)
interface MyMenuChatNavigationItem
