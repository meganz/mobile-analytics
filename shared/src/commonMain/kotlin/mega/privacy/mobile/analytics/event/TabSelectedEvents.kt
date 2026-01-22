package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.TabSelectedEvent

@TabSelectedEvent(screenName = "ChatScreen", tabName = "Chats")
interface ChatsTab

@TabSelectedEvent(screenName = "ChatScreen", tabName = "Meetings")
interface MeetingsTab

@TabSelectedEvent(screenName = "PhotosScreen", tabName = "Timeline")
interface TimelineTab

@TabSelectedEvent(screenName = "PhotosScreen", tabName = "Albums")
interface AlbumsTab

@TabSelectedEvent(screenName = "HomeScreen", tabName = "Recents")
interface RecentsTab

@TabSelectedEvent(screenName = "HomeScreen", tabName = "Offline")
interface OfflineTab

@TabSelectedEvent(screenName = "SharedItemsScreen", tabName = "Incoming Shares")
interface IncomingSharesTab

@TabSelectedEvent(screenName = "SharedItemsScreen", tabName = "Outgoing Shares")
interface OutgoingSharesTab

@TabSelectedEvent(screenName = "SharedItemsScreen", tabName = "Link Shares")
interface LinkSharesTab

@TabSelectedEvent(screenName = "VideoSectionScreen", tabName = "All videos")
interface AllVideosTab

@TabSelectedEvent(screenName = "VideoSectionScreen", tabName = "Playlists")
interface PlaylistsTab

@TabSelectedEvent(screenName = "DriveScreen", tabName = "Cloud drive")
interface CloudDriveTab

@TabSelectedEvent(screenName = "DriveScreen", tabName = "Syncs")
interface SyncsTab

@TabSelectedEvent(screenName = "Transfers Section Screen", tabName = "Active")
interface ActiveTransfersTab

@TabSelectedEvent(screenName = "Transfers Section Screen", tabName = "Completed")
interface CompletedTransfersTab

@TabSelectedEvent(screenName = "Transfers Section Screen", tabName = "Failed")
interface FailedTransfersTab

@TabSelectedEvent(screenName = "MediaScreen", tabName = "Timeline")
interface MediaScreenTimelineTab

@TabSelectedEvent(screenName = "MediaScreen", tabName = "Albums")
interface MediaScreenAlbumsTab

@TabSelectedEvent(screenName = "MediaScreen", tabName = "Videos")
interface MediaScreenVideosTab

@TabSelectedEvent(screenName = "MediaScreen", tabName = "Playlists")
interface MediaScreenPlaylistsTab