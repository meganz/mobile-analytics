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

@MenuItemEvent(
    menuItem = "Resume Transfers",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationResumeTransfersMenuItem

@MenuItemEvent(
    menuItem = "Retry",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat conversation screen"
)
interface ChatConversationRetryMenuItem

@MenuItemEvent(
    menuItem = "Choose from Photos",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeChooseFromPhotosMenuToolbar

@MenuItemEvent(
    menuItem = "Import from files",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeImportFromFilesMenuToolbar

@MenuItemEvent(
    menuItem = "Choose from Photos",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveChooseFromPhotosMenuToolbar

@MenuItemEvent(
    menuItem = "Import from files",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveImportFromFilesMenuToolbar

@MenuItemEvent(
    menuItem = "Add Menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive Screen"
)
interface CloudDriveAddMenu

@MenuItemEvent(
    menuItem = "Start Conversation Menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat Rooms Screen"
)
interface ChatRoomsStartConversationMenu

@MenuItemEvent(
    menuItem = "New Folder",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveNewFolderMenuToolbar

@MenuItemEvent(
    menuItem = "New text file",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveNewTextFileMenuToolbar

@MenuItemEvent(
    menuItem = "Upload files",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeUploadFilesMenuToolbar

@MenuItemEvent(
    menuItem = "Upload folder",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeUploadFolderMenuToolbar

@MenuItemEvent(
    menuItem = "New Text File",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeNewTextFileMenuToolbar

@MenuItemEvent(
    menuItem = "Upload files",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveUploadFilesMenuToolbar

@MenuItemEvent(
    menuItem = "Upload folder",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveUploadFolderMenuToolbar

@MenuItemEvent(
    menuItem = "overflow",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface ToolbarOverflowMenuItem

@MenuItemEvent(
    menuItem = "Cancel subscription",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "My Account Home Screen"
)
interface CancelSubscriptionMenuToolbar

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive Screen"
)
interface CloudDriveHideNodeMenuItem


@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Timeline Screen"
)
interface TimelineHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Album Content Screen"
)
interface AlbumContentHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface HomeHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Image Preview Screen"
)
interface ImagePreviewHideNodeMenuToolBar

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Video Player Screen"
)
interface VideoPlayerHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Document Preview Screen"
)
interface DocumentPreviewHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Audio Player Screen"
)
interface AudioPlayerHideNodeMenuItem

@MenuItemEvent(
    menuItem = "Meetings Add Menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Chat Rooms Screen"
)
interface MeetingsAddMenu

@MenuItemEvent(
    menuItem = "Title A - Z",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface SortPasswordsByTitleAscendingMenuItem

@MenuItemEvent(
    menuItem = "Title Z - A",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface SortPasswordsByTitleDescendingMenuItem

@MenuItemEvent(
    menuItem = "Date added (newest)",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface SortPasswordsByDateNewestMenuItem

@MenuItemEvent(
    menuItem = "Date added (oldest)",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface SortPasswordsByDateOldestMenuItem

@MenuItemEvent(
    menuItem = "Copy password",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface CopyPasswordMenuItem

@MenuItemEvent(
    menuItem = "Copy username",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface CopyUserNameMenuItem

@MenuItemEvent(
    menuItem = "Launch website",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface LaunchWebsiteMenuItem

@MenuItemEvent(
    menuItem = "Edit item",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface EditPasswordMenuItem

@MenuItemEvent(
    menuItem = "Delete item",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Home Screen"
)
interface DeletePasswordMenuItem

@MenuItemEvent(
    menuItem = "Clear copied data in 15 seconds",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Settings Screen"
)
interface ClearCopiedDataAfterFifteenSecondsMenuItem

@MenuItemEvent(
    menuItem = "Clear copied data in 30 seconds",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Settings Screen"
)
interface ClearCopiedDataAfterThirtySecondsMenuItem

@MenuItemEvent(
    menuItem = "Clear copied data in 1 minute",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Settings Screen"
)
interface ClearCopiedDataAfterOneMinuteMenuItem

@MenuItemEvent(
    menuItem = "Clear copied data in 3 minutes",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Settings Screen"
)
interface ClearCopiedDataAfterThreeMinutesMenuItem

@MenuItemEvent(
    menuItem = "Never clear copied data",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Settings Screen"
)
interface NeverClearCopiedDataMenuItem

@MenuItemEvent(
    menuItem = "Share meeting link",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "CallScreen"
)
interface CallScreenMenuOptionsShareLinkMenuItem

@MenuItemEvent(
    menuItem = "Archived",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ArchivedChatsMenuItem

@MenuItemEvent(
    menuItem = "Online Status Changed",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ChatRoomStatusMenuItem

@MenuItemEvent(
    menuItem = "DND Status Changed",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface ChatRoomDNDMenuItem

@MenuItemEvent(
    menuItem = "Open Link",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Meetings list screen"
)
interface OpenLinkMenuItem

@MenuItemEvent(
    menuItem = "Edit",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorEditMenuToolbar

@MenuItemEvent(
    menuItem = "Edit",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorEditMenuItem

@MenuItemEvent(
    menuItem = "Make available offline",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorMakeAvailableOfflineMenuToolbar

@MenuItemEvent(
    menuItem = "Make available offline",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorMakeAvailableOfflineMenuItem

@MenuItemEvent(
    menuItem = "Download",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorDownloadMenuToolbar

@MenuItemEvent(
    menuItem = "Share Link",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorShareLinkMenuToolbar

@MenuItemEvent(
    menuItem = "Share Link",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorShareLinkMenuItem

@MenuItemEvent(
    menuItem = "Close",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorCloseMenuToolbar

@MenuItemEvent(
    menuItem = "Send to chat",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorSendToChatMenuToolbar

@MenuItemEvent(
    menuItem = "Send to chat",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorSendToChatMenuItem

@MenuItemEvent(
    menuItem = "Export file",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorExportFileMenuToolbar

@MenuItemEvent(
    menuItem = "Export file",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorExportFileMenuItem

@MenuItemEvent(
    menuItem = "Rename",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorRenameMenuItem

@MenuItemEvent(
    menuItem = "Move",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorMoveMenuItem

@MenuItemEvent(
    menuItem = "Copy",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorCopyMenuItem

@MenuItemEvent(
    menuItem = "Show line numbers",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorShowLineNumbersMenuItem

@MenuItemEvent(
    menuItem = "Hide line numbers",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorHideLineNumbersMenuItem

@MenuItemEvent(
    menuItem = "Move to the Rubbish bin",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Text Editor Screen"
)
interface TextEditorMoveToTheRubbishBinMenuItem

@MenuItemEvent(
    menuItem = "Save Edit",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Text Editor Screen"
)
interface TextEditorSaveEditMenuToolbar


@MenuItemEvent(
    menuItem = "Hide node from context menu",
    menuType = MenuItemEvent.MenuType.Item,
)
interface HideNodeMenuItem

@MenuItemEvent(
    menuItem = "Hide node from multi-select menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
)
interface HideNodeMultiSelectMenuItem

@MenuItemEvent(
    menuItem = "Magnifier menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Image Preview Screen",
)
interface MagnifierMenuItem

@MenuItemEvent(
    menuItem = "Slideshow tutorial menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Slideshow Screen",
)
interface SlideshowTutorialMenuItem

@MenuItemEvent(
    menuItem = "Photo editor toolbar menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Image Preview Screen",
)
interface PhotoEditorMenuItem

@MenuItemEvent(
    menuItem = "Get link toolbar menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Image Preview Screen",
)
interface ImagePreviewGetLinkMenuItem

@MenuItemEvent(
    menuItem = "Active transfers global play menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersGlobalPlayMenuItem

@MenuItemEvent(
    menuItem = "Active transfers global pause menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersGlobalPauseMenuItem

@MenuItemEvent(
    menuItem = "Active transfers more options menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersMoreOptionsMenuItem

@MenuItemEvent(
    menuItem = "Active transfers select menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersSelectMenuItem

@MenuItemEvent(
    menuItem = "Active transfers cancel selected menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersCancelSelectedMenuItem

@MenuItemEvent(
    menuItem = "Active transfers select all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersSelectAllMenuItem

@MenuItemEvent(
    menuItem = "Active transfers cancel all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface ActiveTransfersCancelAllMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers more options menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersMoreOptionsMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers item view in folder menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersItemViewInFolderMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers item open menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersItemOpenMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers item share menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersItemShareMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers item clear menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersItemClearMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers clear selected menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersClearSelectedMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers select all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersSelectAllMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers select menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersSelectMenuItem

@MenuItemEvent(
    menuItem = "Completed transfers clear all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface CompletedTransfersClearAllMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers more options menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersMoreOptionsMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers item more options menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface FailedTransfersItemMoreOptionsMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers item retry menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface FailedTransfersItemRetryMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers item clear menu",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Transfers Section Screen"
)
interface FailedTransfersItemClearMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers select menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersSelectMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers retry all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersRetryAllMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers clear all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersClearAllMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers clear selected menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersClearSelectedMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers retry selected menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersRetrySelectedMenuItem

@MenuItemEvent(
    menuItem = "Failed transfers select all menu",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Transfers Section Screen"
)
interface FailedTransfersSelectAllMenuItem

@MenuItemEvent(
    menuItem = "Label added",
    menuType = MenuItemEvent.MenuType.Item
)
interface LabelAddedMenuItem

@MenuItemEvent(
    menuItem = "Label removed",
    menuType = MenuItemEvent.MenuType.Item
)
interface LabelRemovedMenuItem

@MenuItemEvent(
    menuItem = "Make available offline using BGContinuedProcessingTask",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Photo preview screen"
)
interface PhotoPreviewMakeAvailableOfflineBGContinuedProcessingTaskMenuItem

@MenuItemEvent(
    menuItem = "Camera uploads settings",
    menuType = MenuItemEvent.MenuType.Toolbar
)
interface CameraUploadsSettingsMenuItem

@MenuItemEvent(
    menuItem = "Info",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveInfoMenuItem

@MenuItemEvent(
    menuItem = "Select",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveSelectMenuItem

@MenuItemEvent(
    menuItem = "Favourite",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveFavouriteMenuItem

@MenuItemEvent(
    menuItem = "Label",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveLabelMenuItem

@MenuItemEvent(
    menuItem = "Make available offline",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveDownloadMenuItem

@MenuItemEvent(
    menuItem = "Share link",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveShareLinkMenuItem

@MenuItemEvent(
    menuItem = "Share folder",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveShareFolderMenuItem

@MenuItemEvent(
    menuItem = "Rename",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveRenameMenuItem

@MenuItemEvent(
    menuItem = "Hide",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveHideMenuItem

@MenuItemEvent(
    menuItem = "Move",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveMoveMenuItem

@MenuItemEvent(
    menuItem = "Copy",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveCopyMenuItem

@MenuItemEvent(
    menuItem = "Move to rubbish bin",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveMoveToRubbishBinMenuItem

@MenuItemEvent(
    menuItem = "Sort by name",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByNameMenuItem

@MenuItemEvent(
    menuItem = "Sort by favourite",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByFavouriteMenuItem

@MenuItemEvent(
    menuItem = "Sort by label",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByLabelMenuItem

@MenuItemEvent(
    menuItem = "Sort by date added",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByDateAddedMenuItem

@MenuItemEvent(
    menuItem = "Sort by date modified",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByDateModifiedMenuItem

@MenuItemEvent(
    menuItem = "Sort by size",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortBySizeMenuItem

@MenuItemEvent(
    menuItem = "Sort by link creation",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByLinkCreationMenuItem

@MenuItemEvent(
    menuItem = "Sort by share creation",
    menuType = MenuItemEvent.MenuType.Item
)
interface SortByShareCreationMenuItem

@MenuItemEvent(
    menuItem = "Scan document",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveScanDocumentMenuToolbar

@MenuItemEvent(
    menuItem = "Capture",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Cloud Drive Screen"
)
interface CloudDriveCaptureMenuToolbar

@MenuItemEvent(
    menuItem = "List view mode option",
    menuType = MenuItemEvent.MenuType.Item,
)
interface ViewModeListMenuItem

@MenuItemEvent(
    menuItem = "Grid view mode option",
    menuType = MenuItemEvent.MenuType.Item,
)
interface ViewModeGridMenuItem

@MenuItemEvent(
    menuItem = "Gallery view mode option",
    menuType = MenuItemEvent.MenuType.Item,
)
interface ViewModeGalleryMenuItem

@MenuItemEvent(
    menuItem = "Cloud drive bottom toolbar more action",
    menuType = MenuItemEvent.MenuType.Item,
)
interface CloudDriveBottomToolBarMoreMenuItem

@MenuItemEvent(
    menuItem = "Scan Document",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeScanDocumentMenuToolbar

@MenuItemEvent(
    menuItem = "Capture",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeCaptureMenuToolbar

@MenuItemEvent(
    menuItem = "Add New Sync",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeAddNewSyncMenuToolbar

@MenuItemEvent(
    menuItem = "Add New Backup",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeAddNewBackupMenuToolbar

@MenuItemEvent(
    menuItem = "New Chat",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Home Screen"
)
interface HomeNewChatMenuToolbar

@MenuItemEvent(
    menuItem = "Add to album from context menu",
    menuType = MenuItemEvent.MenuType.Item,
)
interface AddToAlbumMenuItem

@MenuItemEvent(
    menuItem = "Transfers",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Media Screen"
)
interface MediaScreenTransfersMenuToolbar

@MenuItemEvent(
    menuItem = "Search",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Media Screen"
)
interface MediaScreenSearchMenuToolbar

@MenuItemEvent(
    menuItem = "More",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Media Screen"
)
interface MediaScreenMoreMenuToolbar

@MenuItemEvent(
    menuItem = "Sort by",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Media Screen"
)
interface MediaScreenSortByMenuToolbar

@MenuItemEvent(
    menuItem = "Settings",
    menuType = MenuItemEvent.MenuType.Toolbar,
    screen = "Media Screen"
)
interface MediaScreenSettingsMenuToolbar


@MenuItemEvent(
    menuItem = "Sync",
    menuType = MenuItemEvent.MenuType.Item,
    screen = "Cloud Drive screen"
)
interface CloudDriveSyncMenuItem