package mega.privacy.mobile.analytics.event

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