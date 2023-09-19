package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent

@MenuItemEvent(
    menuItem = "Share link tapped for folder",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkShareLinkTapFolderMenuItem

@MenuItemEvent(
    menuItem = "Share link tapped for folder",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkShareLinkTapFolderMenuToolbar

@MenuItemEvent(
    menuItem = "Share link tapped for file",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkShareLinkTapFileMenuItem

@MenuItemEvent(
    menuItem = "Share link tapped for file",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkShareLinkTapFileMenuToolbar

@MenuItemEvent(
    menuItem = "Manage link tapped for folder",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkManageLinkTapFolderMenuItem

@MenuItemEvent(
    menuItem = "Manage link tapped for folder",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkManageLinkTapFolderMenuToolbar

@MenuItemEvent(
    menuItem = "Manage link tapped for file",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkManageLinkTapFileMenuItem

@MenuItemEvent(
    menuItem = "Manage link tapped for file",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkManageLinkTapFileMenuToolbar

@MenuItemEvent(
    menuItem = "Manage link for multiple Nodes",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkGetLinkForNodesMenuItem

@MenuItemEvent(
    menuItem = "Manage link for multiple Nodes",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkGetLinkForNodesMenuToolbar

@MenuItemEvent(
    menuItem = "Share link for multiple Nodes",
    menuType = MenuItemEvent.MenuType.Item
)
interface LinkShareLinkForNodesMenuItem

@MenuItemEvent(
    menuItem = "Share link for multiple Nodes",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface LinkShareLinkForNodesMenuToolbar

@MenuItemEvent(
    menuItem = "Album share get link ",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Album content screen",
)
interface AlbumContentShareLinkMenuToolbar

@MenuItemEvent(
    menuItem = "Albums share get links",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Album list screen",
)
interface AlbumListShareLinkMenuItem

@MenuItemEvent(
    menuItem = "Search",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeScreenSearchMenuToolbar

@MenuItemEvent(
    menuItem = "Search",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud drive screen"
)
interface CloudDriveSearchMenuToolbar

@MenuItemEvent(
    menuItem = "Open with",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Search results screen"
)
interface SearchResultOpenWithMenuItem

@MenuItemEvent(
    menuItem = "Save to device",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Search results screen"
)
interface SearchResultSaveToDeviceMenuItem

@MenuItemEvent(
    menuItem = "Get link",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Search results screen"
)
interface SearchResultGetLinkMenuItem

@MenuItemEvent(
    menuItem = "Share",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Search results screen"
)
interface SearchResultShareMenuItem

@MenuItemEvent(
    menuItem = "Overflow",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Search results screen"
)
interface SearchResultOverflowMenuItem

@MenuItemEvent(
    menuItem = "Info",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Video Player"
)
interface VideoPlayerInfoMenuItem


@MenuItemEvent(
    menuItem = "Save To Device",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Video Player"
)
interface VideoPlayerSaveToDeviceMenuToolbar

@MenuItemEvent(
    menuItem = "Send To Chat",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Video Player"
)
interface VideoPlayerSendToChatMenuToolbar

@MenuItemEvent(
    menuItem = "Share",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Video Player"
)
interface VideoPlayerShareMenuToolbar

@MenuItemEvent(
    menuItem = "Get Link",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Video Player"
)
interface VideoPlayerGetLinkMenuToolbar

@MenuItemEvent(
    menuItem = "Remove Link",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Video Player"
)
interface VideoPlayerRemoveLinkMenuToolbar