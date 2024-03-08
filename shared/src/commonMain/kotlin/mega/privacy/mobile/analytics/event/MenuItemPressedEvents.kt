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

@MenuItemEvent(
    menuItem = "Album share get link ",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Album content screen",
)
interface AlbumContentShareLinkMenuToolbar

@MenuItemEvent(
    menuItem = "Albums share get links",
    menuType = MenuItemEvent.MenuType.Toolbar,
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

@MenuItemEvent(
    menuItem = "Play Slideshow",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Image Preview",
)
interface PlaySlideshowMenuToolbar

@MenuItemEvent(
    menuItem = "Schedule meeting",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ScheduleMeetingMenuItem

@MenuItemEvent(
    menuItem = "Edit scheduled meeting",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ScheduledMeetingEditMenuItem

@MenuItemEvent(
    menuItem = "Edit scheduled meeting",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Scheduled meeting info screen"
)
interface ScheduledMeetingEditMenuToolbar

@MenuItemEvent(
    menuItem = "Cancel schedule meeting",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ScheduledMeetingCancelMenuItem

@MenuItemEvent(
    menuItem = "Album photos selection filter",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Album photos selection screen"
)
interface AlbumPhotosSelectionFilterMenuToolbar

@MenuItemEvent(
    menuItem = "Album content delete album",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Album content screen"
)
interface AlbumContentDeleteAlbum

@MenuItemEvent(
    menuItem = "Album list delete albums",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Albums list screen"
)
interface AlbumsListDeleteAlbums

@MenuItemEvent(
    menuItem = "Album content remove items",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Album content screen"
)
interface AlbumContentRemoveItems

@MenuItemEvent(
    menuItem = "Save To device",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Photo preview screen"
)
interface PhotoPreviewSaveToDeviceMenuToolbar

@MenuItemEvent(
    menuItem = "Clear resolved issues",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Android Sync List screen"
)
interface AndroidSyncClearResolvedIssues

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Rename All Items",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncRenameAllItems

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Remove Duplicates",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncRemoveDuplicates

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Merge Folders",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncMergeFolders

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Remove Duplicates and Remove Rest",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncRemoveDuplicatesAndRemoveRest

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Choose Local File",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncChooseLocalFile

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Choose Remote File",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncChooseRemoteFile

@MenuItemEvent(
    menuItem = "Android Sync Stalled Issue Resolution Choose LatestModified time",
    menuType = MenuItemEvent.MenuType.Item
)
interface AndroidSyncChooseLatestModifiedTime

@MenuItemEvent(
    menuItem = "Forward Message",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationForwardActionMenu

@MenuItemEvent(
    menuItem = "Share Message",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationShareActionMenu

@MenuItemEvent(
    menuItem = "Add To Cloud Drive",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationAddToCloudDriveActionMenu

@MenuItemEvent(
    menuItem = "Download",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationDownloadActionMenu

@MenuItemEvent(
    menuItem = "Invite",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationInviteActionMenu

@MenuItemEvent(
    menuItem = "Start Conversation",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationSendMessageActionMenu

@MenuItemEvent(
    menuItem = "Edit Message",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationEditActionMenu

@MenuItemEvent(
    menuItem = "Copy Text Message",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationCopyActionMenu

@MenuItemEvent(
    menuItem = "Delete Message",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationDeleteActionMenu

@MenuItemEvent(
    menuItem = "Back Button (Arrow Left)",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationHomeUpMenuToolbar

@MenuItemEvent(
    menuItem = "Call",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationCallMenuToolbar

@MenuItemEvent(
    menuItem = "Video",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationVideoMenuToolbar

@MenuItemEvent(
    menuItem = "Select",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationSelectMenuToolbar

@MenuItemEvent(
    menuItem = "Add Participants",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationAddParticipantsMenuToolbar

@MenuItemEvent(
    menuItem = "Info",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationInfoMenuToolbar

@MenuItemEvent(
    menuItem = "Clear",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationClearMenuToolbar

@MenuItemEvent(
    menuItem = "Archive",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationArchiveMenuToolbar

@MenuItemEvent(
    menuItem = "Unarchive",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationUnarchiveMenuToolbar

@MenuItemEvent(
    menuItem = "Leave",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationLeaveMenuToolbar

@MenuItemEvent(
    menuItem = "End Call For All",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationEndCallForAllMenuToolbar

@MenuItemEvent(
    menuItem = "Mute",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationMuteMenuToolbar

@MenuItemEvent(
    menuItem = "Unmute",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Chat conversation screen"
)
interface ChatConversationUnmuteMenuToolbar

@MenuItemEvent(
    menuItem = "Open with",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationOpenWithActionMenuItem

@MenuItemEvent(
    menuItem = "Forward",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationForwardActionMenuItem

@MenuItemEvent(
    menuItem = "Edit",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationEditActionMenuItem

@MenuItemEvent(
    menuItem = "Copy",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationCopyActionMenuItem

@MenuItemEvent(
    menuItem = "Share",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationShareActionMenuItem

@MenuItemEvent(
    menuItem = "Select",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationSelectActionMenuItem

@MenuItemEvent(
    menuItem = "View contacts",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationViewContactsActionMenuItem

@MenuItemEvent(
    menuItem = "Info",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationInfoActionMenuItem

@MenuItemEvent(
    menuItem = "Send message",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationSendMessageActionMenuItem

@MenuItemEvent(
    menuItem = "Invite",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationInviteActionMenuItem

@MenuItemEvent(
    menuItem = "Add to cloud drive",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationAddToCloudDriveActionMenuItem

@MenuItemEvent(
    menuItem = "Save to device",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationSaveToDeviceActionMenuItem

@MenuItemEvent(
    menuItem = "Available offline",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationAvailableOfflineActionMenuItem

@MenuItemEvent(
    menuItem = "Remove",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationRemoveActionMenuItem

@MenuItemEvent(
    menuItem = "Take picture",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationTakePictureMenuItem

@MenuItemEvent(
    menuItem = "Gallery",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationGalleryMenuItem

@MenuItemEvent(
    menuItem = "File",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationFileMenuItem

@MenuItemEvent(
    menuItem = "Voice",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationVoiceMenuItem

@MenuItemEvent(
    menuItem = "Video",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationVideoMenuItem

@MenuItemEvent(
    menuItem = "Scan",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationScanMenuItem

@MenuItemEvent(
    menuItem = "GIF",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationGIFMenuItem

@MenuItemEvent(
    menuItem = "Location",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationLocationMenuItem

@MenuItemEvent(
    menuItem = "Contact",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationContactMenuItem

@MenuItemEvent(
    menuItem = "Voice clip",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationVoiceClipMenuItem
