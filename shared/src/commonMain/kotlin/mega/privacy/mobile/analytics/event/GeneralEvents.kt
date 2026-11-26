package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.GeneralEvent

@GeneralEvent
interface ImportAlbumContentLoaded

@GeneralEvent
interface PasscodeEntered

@GeneralEvent
interface InAppUpdateDownloadSuccessMessageDisplayed

@GeneralEvent
interface VideoPlayerIsActivated

@GeneralEvent
interface WaitingRoomTimeout

@GeneralEvent
interface OnboardingUpsellingDialogVariantBProPlanIIIDisplayed

@GeneralEvent
class AccountRegistration(
    val referrerUrl: String?,
    val referrerClickTime: Long?,
    val appInstallTime: Long?
)

@GeneralEvent
class AccountActivated

@GeneralEvent
interface NodeInfoDescriptionEntered

@GeneralEvent
interface NodeInfoDescriptionConfirmed

@GeneralEvent
interface NodeInfoDescriptionAddedMessageDisplayed

@GeneralEvent
interface NodeInfoDescriptionUpdatedMessageDisplayed

@GeneralEvent
interface NodeInfoDescriptionCharacterLimit

@GeneralEvent
interface SubscriptionSuccessful

@GeneralEvent
interface SubscriptionFailed

@GeneralEvent
interface SubscriptionCancelled

@GeneralEvent
interface MultiFactorAuthVerificationSuccess

@GeneralEvent
interface MultiFactorAuthVerificationFailed

@GeneralEvent
interface NodeInfoTagsEntered

@GeneralEvent
interface NodeInfoTagsAdded

@GeneralEvent
interface NodeInfoTagsRemoved

@GeneralEvent
interface NodeInfoTagsLimitErrorDisplayed

@GeneralEvent
interface NodeInfoTagsLengthErrorDisplayed

@GeneralEvent
interface NodeInfoTagsProOnlyEntered

@GeneralEvent
interface UpgradeAccountCancelled

@GeneralEvent
interface AccountScreenHeaderTapped

@GeneralEvent
interface UpgradeAccountPurchaseSucceeded

@GeneralEvent
interface UpgradeAccountPurchaseFailed

@GeneralEvent
interface NodeInfoDescriptionRemovedMessageDisplayed

@GeneralEvent
interface PasswordItemsSearch

@GeneralEvent
interface SyncPromotionBottomSheetDismissed

@GeneralEvent
interface TestPasswordConfirmWrongPasswordMessageDisplayed

@GeneralEvent
interface TestPasswordConfirmPasswordAcceptedMessageDisplayed

@GeneralEvent
interface VpnSplitTunnellingEnableSelectedApps

@GeneralEvent
interface VpnSplitTunnellingDisableSelectedApps

@GeneralEvent
interface AccountNotificationCentreDisplayedWithNoUnreadNotifications

@GeneralEvent
interface VpnSplitTunnellingEnableAllApps

@GeneralEvent
interface AccountNotificationCentreDisplayedWithUnreadNotifications

@GeneralEvent
interface DocumentScanInitiated

@GeneralEvent
interface DocumentScannerSavePDFToCloudDrive

@GeneralEvent
interface DocumentScannerSavePDFToChat

@GeneralEvent
interface DocumentScannerSaveImageToCloudDrive

@GeneralEvent
interface DocumentScannerSaveImageToChat

@GeneralEvent
interface DocumentScannerUploadingPDFToCloudDrive

@GeneralEvent
interface DocumentScannerUploadingPDFToChat

@GeneralEvent
interface DocumentScannerUploadingImageToCloudDrive

@GeneralEvent
interface DocumentScannerUploadingImageToChat

@GeneralEvent
interface AudioPlayerIsActivated

@GeneralEvent
interface AudioPlayerShuffleEnabled

@GeneralEvent
interface AudioPlayerLoopQueueEnabled

@GeneralEvent
interface AudioPlayerLoopPlayingItemEnabled

@GeneralEvent
interface AudioPlayerQueueReordered

@GeneralEvent
interface AndroidSyncLocalFolderSelected

@GeneralEvent
interface SyncFoldersListDisplayed

@GeneralEvent
interface SyncCardExpanded

@GeneralEvent
interface PlaylistCreatedSuccessfully

@GeneralEvent
interface CameraUploadsEnabled

@GeneralEvent
interface CameraUploadsDisabled

@GeneralEvent
interface MediaUploadsEnabled

@GeneralEvent
interface MediaUploadsDisabled

@GeneralEvent
interface CameraUploadsFolderConflictDetected

@GeneralEvent
interface SyncRemoteFolderConflict

@GeneralEvent
interface SyncLocalFolderConflict

@GeneralEvent
interface SyncMegaPickerFolderDisabled

@GeneralEvent
interface SyncDcimFolderSelected

@GeneralEvent
interface SyncExternalStorageFolderSelected

@GeneralEvent
interface AdBlockingEnabled

@GeneralEvent
interface AdBlockingDisabled

@GeneralEvent
class RequestNewCountries(countries: String)

@GeneralEvent
class DebugLogsEnabled

@GeneralEvent
class DebugLogsDisabled

@GeneralEvent
class SmartBannerSwipe

@GeneralEvent
interface AudioPlayerSpeedChangeHalfX

@GeneralEvent
interface AudioPlayerSpeedChangeTo_0_75X

@GeneralEvent
interface AudioPlayerSpeedChange1X

@GeneralEvent
interface AudioPlayerSpeedChangeTo_1_25X

@GeneralEvent
interface AudioPlayerSpeedChangeOneAndHalfX

@GeneralEvent
interface AudioPlayerSpeedChangeTo_1_75X

@GeneralEvent
interface AudioPlayerSpeedChange2X

@GeneralEvent
interface AudioPlayerForward15Seconds

@GeneralEvent
interface AudioPlayerBack15Seconds

@GeneralEvent
interface AudioPlayerQueueItemRemoved

@GeneralEvent
interface VideoUploadsEnabled

@GeneralEvent
interface VideoUploadsDisabled

@GeneralEvent
interface CameraUploadsFormatHEICSelected

@GeneralEvent
interface CameraUploadsFormatJPGSelected

@GeneralEvent
interface MegaUploadFolderUpdated

@GeneralEvent
interface PhotosLocationTagsEnabled

@GeneralEvent
interface PhotosLocationTagsDisabled

@GeneralEvent
interface CameraUploadsMobileDataEnabled

@GeneralEvent
interface CameraUploadsMobileDataDisabled

@GeneralEvent
interface CameraUploadsKeepFileNamesAsInDeviceEnabled

@GeneralEvent
interface CameraUploadsKeepFileNamesAsInDeviceDisabled

@GeneralEvent
interface CameraUploadsOnlyWhileChargingEnabled

@GeneralEvent
interface CameraUploadsOnlyWhileChargingDisabled

@GeneralEvent
interface CameraUploadsVideoCompressionRequireChargingEnabled

@GeneralEvent
interface CameraUploadsVideoCompressionRequireChargingDisabled

@GeneralEvent
interface CameraUploadsVideoCompressionSizeLimitChanged

@GeneralEvent
interface CameraUploadsFileUploadPhotosSelected

@GeneralEvent
interface CameraUploadsFileUploadVideosSelected

@GeneralEvent
interface CameraUploadsFileUploadPhotosAndVideosSelected

@GeneralEvent
interface CameraUploadsLocalFolderSelected

@GeneralEvent
interface CameraUploadsTargetFolderSelected

@GeneralEvent
interface MediaUploadsLocalFolderSelected

@GeneralEvent
interface MediaUploadsTargetFolderSelected

@GeneralEvent
interface LivePhotoVideoUploadsEnabled

@GeneralEvent
interface LivePhotoVideoUploadsDisabled

@GeneralEvent
interface BurstPhotosUploadEnabled

@GeneralEvent
interface BurstPhotosUploadDisabled

@GeneralEvent
interface HiddenAlbumUploadEnabled

@GeneralEvent
interface HiddenAlbumUploadDisabled

@GeneralEvent
interface SharedAlbumsUploadEnabled

@GeneralEvent
interface SharedAlbumsUploadDisabled

@GeneralEvent
interface ITunesSyncedAlbumsUploadEnabled

@GeneralEvent
interface ITunesSyncedAlbumsUploadDisabled

@GeneralEvent
interface UploadOnlyNewPhotosEnabled

@GeneralEvent
interface UploadOnlyNewPhotosDisabled

@GeneralEvent
interface VideoCodecHEVCSelected

@GeneralEvent
interface VideoCodecH264Selected

@GeneralEvent
interface VideoQualityLow

@GeneralEvent
interface VideoQualityMedium

@GeneralEvent
interface VideoQualityHigh

@GeneralEvent
interface VideoQualityOriginal

@GeneralEvent
interface SlideshowSecureModeActivated

@GeneralEvent
interface IOSMigrationFileCreatedSuccessfully

@GeneralEvent
interface IOSMigrationFileImportedSuccessfully

@GeneralEvent
interface IOSKMTransferCreatedSuccessfully

@GeneralEvent
interface IOSKMTransferImportedSuccessfully

@GeneralEvent
interface VideoBufferingExceeded_1_Second

@GeneralEvent
class VideoPlaybackRecord(val duration: Int)

@GeneralEvent
class FileOpen(
    val fileType: String,
    val context: FileOpenContext
) {
    enum class FileOpenContext {
        /**
         * Opened from a file link
         */
        FileLink,
        /**
         * Opened from chat
         */
        Chat,
        /**
         * Opened from the cloud drive
         */
        CloudDrive,
        /**
         * Opened from shared items
         */
        SharedItems,
        /**
         * Opened from recent files
         */
        Recent,
        /**
         * Opened from offline files
         */
        Offline,
        /**
         * Opened from search results
         */
        Search,
        /**
         * Opened from a folder link
         */
        FolderLink,
        /**
         * Context is unknown
         */
        Unknown
    }
}

@GeneralEvent
class VideoPlaybackFirstFrame(val time: Int, val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}

@GeneralEvent
class VideoPlaybackStartupFailure(val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}

@GeneralEvent
class VideoPlaybackStall(val time: Int, val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}


@GeneralEvent
class VideoPlaybackRecordNewVP(val duration: Int)

@GeneralEvent
class VideoPlaybackFirstFrameNewVP(val time: Int, val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}

@GeneralEvent
class VideoPlaybackStartupFailureNewVP(val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}

@GeneralEvent
class VideoPlaybackStallNewVP(val time: Int, val scenario: VideoPlaybackScenario, val commonMap: String) {
    enum class VideoPlaybackScenario {
        /**
         * manual click play
         */
        ManualClick,

        /**
         * resume play
         */
        Resume,

        /**
         * replay
         */
        Replay
    }
}

@GeneralEvent
interface CloudDriveMultiSelectModeEntered

@GeneralEvent
interface SyncWorkerForegroundExecutionStarted

@GeneralEvent
interface AirplayActivationInVideoPlayback

@GeneralEvent
interface MyAccountStorageTransferSectionTapped

@GeneralEvent
interface MyAccountBackupRecoverySectionTapped

@GeneralEvent
interface MyAccountContactsSectionTapped

@GeneralEvent
interface MyAccountAchievementsSectionTapped

@GeneralEvent
class MediaScreenFilterSettingsRememberPreferences(val enabled: Boolean)

// BGTask (BGContinuedProcessingTask) analytics events

// BGTask outcome
@GeneralEvent
interface BGTaskCompleted

@GeneralEvent
interface BGTaskExpired

@GeneralEvent
interface BGTaskScheduleFailedRegister

@GeneralEvent
interface BGTaskScheduleFailedSubmit

// Duration of BGTask (start to completion/expiration) - bucketed
@GeneralEvent
interface BGTaskDurationUnder1Min

@GeneralEvent
interface BGTaskDurationUnder5Min

@GeneralEvent
interface BGTaskDurationUnder10Min

@GeneralEvent
interface BGTaskDurationOver10Min

// Completion % at expiration - bucketed
@GeneralEvent
interface BGTaskProgressAtExpirationUnder25

@GeneralEvent
interface BGTaskProgressAtExpirationUnder50

@GeneralEvent
interface BGTaskProgressAtExpirationUnder75

@GeneralEvent
interface BGTaskProgressAtExpirationOver75

// App state at expiration
@GeneralEvent
interface BGTaskExpiredWhileAppActive

@GeneralEvent
interface BGTaskExpiredWhileAppBackground

// OS scheduling delay (time from submit to launch handler) - bucketed
@GeneralEvent
interface BGTaskSchedulingDelayUnder5s

@GeneralEvent
interface BGTaskSchedulingDelayUnder30s

@GeneralEvent
interface BGTaskSchedulingDelayUnder1Min

@GeneralEvent
interface BGTaskSchedulingDelayOver1Min

@GeneralEvent
class DownloadConnectionsChanged(val previousValue: Int, val newValue: Int)

// Rewarded Ad funnel events
@GeneralEvent
interface RewardedAdGateActionRequested

@GeneralEvent
interface RewardedAdLoaded

@GeneralEvent
interface RewardedAdImpression

@GeneralEvent
interface RewardedAdClicked

@GeneralEvent
interface RewardedAdRewardEarned

@GeneralEvent
interface RewardedAdUnavailable

@GeneralEvent
class UploadConnectionsChanged(val previousValue: Int, val newValue: Int)

@GeneralEvent
interface OpenLinkUrlSubmitted

@GeneralEvent
interface OpenLinkUrlSuccess

@GeneralEvent
interface OpenLinkUrlFailure

@GeneralEvent
interface CloudDriveDocumentProviderFileOpened

@GeneralEvent
interface CloudDriveDocumentProviderFolderOpened

@GeneralEvent
class ShareLinkOpened(val linkType: LinkType, val authStatus: AuthStatus) {
    enum class LinkType {
        File,
        Folder,
        Album
    }
    enum class AuthStatus {
        LoggedIn,
        LoggedOut
    }
}

@GeneralEvent
class VideoPlayStarted(val linkType: LinkType, val authStatus: AuthStatus) {
    enum class LinkType {
        File,
        Folder,
        Album
    }
    enum class AuthStatus {
        LoggedIn,
        LoggedOut
    }
}

@GeneralEvent
class AudioPlayStarted(val linkType: LinkType, val authStatus: AuthStatus) {
    enum class LinkType {
        File,
        Folder,
        Album
    }
    enum class AuthStatus {
        LoggedIn,
        LoggedOut
    }
}

@GeneralEvent
interface VideoPlaybackMp4Started

@GeneralEvent
interface VideoPlaybackAviStarted

@GeneralEvent
interface VideoPlaybackMkvStarted

@GeneralEvent
interface VideoPlaybackMovStarted

@GeneralEvent
interface VideoPlaybackOtherStarted

@GeneralEvent
class PdfViewerSearchPerformed(val resultCount: Int)

@GeneralEvent
interface LinkSettingsSaveFailed

@GeneralEvent
interface LinkShareSheetCompleted

@GeneralEvent
interface LinkExpiredStateDisplayed

@GeneralEvent
interface LinkExpiryDateChanged

@GeneralEvent
class LinkCreationFailed(val reason: String)

@GeneralEvent
class VideoPlaybackStartupFailureReasonNewVP(val errCode: Int, val reason: String)

@GeneralEvent
interface InactivityPurgeBannerDisplayed

@GeneralEvent
interface InactivityPurgeEventReceived

@GeneralEvent
interface IOSKMTransferUSMigrationSucceeded

@GeneralEvent
interface IOSKMTransferUSSessionLost

@GeneralEvent
class IOSKMTransferUSMigrationFailed(val reason: String)

@GeneralEvent
interface FreeUserOfferTimedOut

@GeneralEvent
interface PaidUserOfferTimedOut

@GeneralEvent
interface PaidUserEligibleForOffers

@GeneralEvent
interface CustomiseNavigationItemsReordered

@GeneralEvent
interface CustomNavigationActive

@GeneralEvent
class UpgradePlansPurchaseError(val details: String)

@GeneralEvent
class AudioPlaybackStarted(val sourceType: String, val authStatus: AuthStatus) {
    enum class AuthStatus {
        LoggedIn,
        LoggedOut
    }
}

@GeneralEvent
class AudioPlaybackFailed(val sourceType: String, val reason: String, val authStatus: AuthStatus) {
    enum class AuthStatus {
        LoggedIn,
        LoggedOut
    }
}

@GeneralEvent
interface AudioPlayerContinuousPlaybackEnabled

@GeneralEvent
interface AudioPlayerContinuousPlaybackDisabled

@GeneralEvent
interface MediaScreenCreateVideoPlaylistDialogDismissed

@GeneralEvent
interface MediaScreenVideoPlayedFromPlaylist

@GeneralEvent
interface MediaScreenVideosAddedToPlaylist

@GeneralEvent
interface MediaScreenVideosRemovedFromPlaylist

@GeneralEvent
interface MediaScreenVideoPlaylistDeleted

@GeneralEvent
interface MediaScreenVideoPlaylistsBulkDeleted

@GeneralEvent
interface MediaScreenVideoPlaylistRenamed

@GeneralEvent
interface AndroidSyncExclusionsNameRuleAdded

@GeneralEvent
class AndroidSyncExclusionsExtensionRulesAdded(val extensionCount: Int)

@GeneralEvent
interface AndroidSyncExclusionsBiggerThanSizeLimitAdded

@GeneralEvent
interface AndroidSyncExclusionsSmallerThanSizeLimitAdded

@GeneralEvent
interface AndroidSyncExclusionsNotBetweenSizeLimitAdded

@GeneralEvent
interface AndroidSyncExclusionsRuleEdited

@GeneralEvent
interface AndroidSyncExclusionsBiggerThanSizeLimitEdited

@GeneralEvent
interface AndroidSyncExclusionsSmallerThanSizeLimitEdited

@GeneralEvent
interface AndroidSyncExclusionsNotBetweenSizeLimitEdited

@GeneralEvent
interface AndroidSyncExclusionsSizeLimitDeleted

@GeneralEvent
interface AndroidSyncExclusionsRuleDeletedFromChip

@GeneralEvent
interface AndroidSyncExclusionsRuleDeletedFromEditScreen

@GeneralEvent
interface AndroidSyncExclusionsDefaultsRestored

@GeneralEvent
interface AndroidSyncExclusionsAllRemoved

@GeneralEvent
class AndroidSyncExclusionsChangesSaved(
    val ruleCount: Int,
    val customRuleCount: Int,
    val hasSizeLimit: Boolean
)

@GeneralEvent
interface AndroidSyncExclusionsSaveFailed

@GeneralEvent
interface AndroidSyncExclusionsChangesDiscarded

@GeneralEvent
interface AndroidSyncExclusionsReviewSaved

@GeneralEvent
class AndroidSyncExclusionsSetupConfigured(
    val ruleCount: Int,
    val customRuleCount: Int,
    val hasSizeLimit: Boolean
)

/**
 * A plain text (.txt) file was created from the new text file dialog, with the "Rich text
 * formatting" checkbox left off. Paired with [RichTextFileCreated]: the two together are every
 * creation where the checkbox was on offer, so their split is the rich text opt-in rate.
 *
 * Not sent by the new link (.url) dialog, which shares the same screen but never offers the
 * checkbox.
 */
@GeneralEvent
interface PlainTextFileCreated

/**
 * A rich text (Markdown, .md) file was created from the new text file dialog. Sent whether the
 * user ticked the "Rich text formatting" checkbox or typed the .md extension themselves, since
 * the checkbox and the file name field are the same state. See [PlainTextFileCreated] for the
 * pairing.
 */
@GeneralEvent
interface RichTextFileCreated

/**
 * The Continue where you left off carousel was shown on Home with at least one item. Sent at most
 * once per app session (process): not again on scroll, recomposition, rotation, tab switches or
 * returning to Home, and not while the carousel is loading or empty. It counts sessions that saw
 * a non-empty carousel, so pair it with [ContinueWhereLeftOffWidgetItemPressed] per session for
 * the tap-through rate.
 */
@GeneralEvent
interface ContinueWhereLeftOffWidgetDisplayed

@GeneralEvent
interface WidgetCustomisationWidgetsReordered

@GeneralEvent
interface WidgetCustomisationActive

@GeneralEvent
interface PdfViewerClosed

@GeneralEvent
interface PdfViewerLoadSucceeded

@GeneralEvent
class PdfViewerLoadFailed(val reason: String)

@GeneralEvent
interface PdfViewerPasswordUnlocked

@GeneralEvent
interface PdfViewerMoreMenuOpened

@GeneralEvent
interface PdfViewerThumbnailsOpened

@GeneralEvent
interface PdfViewerContentsOpened

@GeneralEvent
interface PdfViewerSearchSessionStarted

@GeneralEvent
class PdfViewerDownloadRequested(val source: String)

/**
 * A long press in the PDF viewer selected text. Dragging a selection handle does not send it.
 */
@GeneralEvent
interface PdfViewerTextSelected

/**
 * "Select all" in the PDF viewer selected text. On Android, it selects the text of the current
 * page.
 */
@GeneralEvent
interface PdfViewerAllTextSelected

/**
 * The user copied the selected text of the PDF viewer. It is not sent when the copied text is
 * empty.
 *
 * @property characterCount The number of UTF-16 code units in the copied text: `String.length`
 * in Kotlin, `text.utf16.count` in Swift.
 */
@GeneralEvent
class PdfViewerTextCopied(val characterCount: Int)

/**
 * Items were pinned to Home from the picker, from the Home section or from the Pinned items page.
 *
 * @property count The number of items pinned in this batch, from 1 to 10
 */
@GeneralEvent
class PinnedItemsPinned(val count: Int)

/**
 * An item was unpinned from Home, from Edit mode on the Pinned items page or from the dialog
 * for an unavailable item.
 */
@GeneralEvent
interface PinnedItemUnpinned

/**
 * The pinned items were reordered on the Pinned items page, by dragging or by the "Move up" and
 * "Move down" accessibility actions.
 */
@GeneralEvent
interface PinnedItemsReordered

/**
 * The external image viewer showed an image that another app sent to MEGA.
 *
 * @property fileType Image type: `jpeg`, `png`, `heic`, `webp`, `gif` or `other`. It comes from
 * the MIME type of the intent, with the file extension as fallback.
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalImageOpened(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The user tapped "Save to Cloud Drive" in the external image viewer. Divide by
 * [ExternalImageOpened] for each [fileType] to get the share of opens that the user saves.
 *
 * @property fileType Same values as [ExternalImageOpened.fileType].
 * @property isLoggedIn True when the user is logged in at the time of the event. It can differ
 * from the value of the [ExternalImageOpened] event, because the user can log in after the open.
 */
@GeneralEvent
class ExternalImageSaveToCloudDriveTapped(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The upload of the image from the external image viewer started.
 *
 * @property fileType Same values as [ExternalImageOpened.fileType].
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalImageUploadStarted(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The external audio player opened an audio file that another app sent to MEGA.
 *
 * @property fileType Audio type: `mp3` or `other`. It comes from the MIME type of the intent, with
 * the file extension as fallback.
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalAudioOpened(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The user tapped "Save to MEGA" in the external audio player. Divide by [ExternalAudioOpened]
 * for each [fileType] to get the share of opens that the user saves.
 *
 * @property fileType Same values as [ExternalAudioOpened.fileType].
 * @property isLoggedIn True when the user is logged in at the time of the event. It can differ
 * from the value of the [ExternalAudioOpened] event, because the user can log in after the open.
 */
@GeneralEvent
class ExternalAudioSaveToCloudDriveTapped(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The upload of the audio file from the external audio player started.
 *
 * @property fileType Same values as [ExternalAudioOpened.fileType].
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalAudioUploadStarted(
    val fileType: String,
    val isLoggedIn: Boolean,
)

/**
 * The PDF viewer opened a PDF that another app sent to MEGA. The event has no file type, because
 * the PDF viewer opens only PDF files.
 *
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalPdfOpened(
    val isLoggedIn: Boolean,
)

/**
 * The user tapped "Save to MEGA" in the PDF viewer for a PDF that another app sent to MEGA.
 * Divide by [ExternalPdfOpened] to get the share of opens that the user saves.
 *
 * @property isLoggedIn True when the user is logged in at the time of the event. It can differ
 * from the value of the [ExternalPdfOpened] event, because the user can log in after the open.
 */
@GeneralEvent
class ExternalPdfSaveToCloudDriveTapped(
    val isLoggedIn: Boolean,
)

/**
 * The upload of a PDF that another app sent to MEGA started.
 *
 * @property isLoggedIn True when the user is logged in at the time of the event.
 */
@GeneralEvent
class ExternalPdfUploadStarted(
    val isLoggedIn: Boolean,
)

@GeneralEvent
interface VideoPlayerOpenedWithShuffleOn

@GeneralEvent
interface AudioPlayerStoredSpeedApplied

@GeneralEvent
interface AudioPlayerPlaybackErrorShown

/**
 * The batch rename dialog was closed without renaming, by Cancel, back or a tap outside.
 */
@GeneralEvent
interface BatchRenameDialogCancelled

/**
 * A batch rename ended and at least 1 item was not renamed.
 */
@GeneralEvent
interface BatchRenamePartiallyFailed

/**
 * A Camera Uploads or Sync background run started, and its lateness was measured against the time
 * the run was due. Only 10 % of the runs less than 3 hours late are sent, and every later run, so
 * the counts are not the total run counts.
 *
 * @property latenessBucket Null when the lateness cannot be calculated, for example after a reboot.
 * @property counted True when the run counted toward the battery restriction prompt.
 * @property longLateCount Number of counted runs in the current window, after this run. Only Camera
 * Uploads runs are counted, so a Sync event carries the Camera Uploads count.
 * @property retry True when the run is a retry after a failed attempt, so its lateness includes the
 * retry delay.
 * @property sdk Android API level of the device.
 */
@GeneralEvent
class BackgroundWorkRunLateness(
    val kind: WorkKind,
    val latenessBucket: LatenessBucket?,
    val counted: Boolean,
    val longLateCount: Int,
    val isIgnoringBatteryOptimizations: Boolean,
    val standbyBucket: StandbyBucket,
    val isBackgroundRestricted: Boolean,
    val powerSave: Boolean,
    val deviceIdle: Boolean,
    val charging: Boolean,
    val reboot: Boolean,
    val update: Boolean,
    val retry: Boolean,
    val lastExit: LastExit,
    val prevUnfinished: Boolean,
    val coldStart: Boolean,
    val sdk: Int,
    val networkConstraint: NetworkConstraint,
) {
    enum class WorkKind {
        /**
         * Camera Uploads worker
         */
        CameraUploads,

        /**
         * Sync worker
         */
        Sync
    }

    /**
     * Each range includes its lower bound and excludes its upper bound.
     */
    enum class LatenessBucket {
        /**
         * The run started before it was due
         */
        Negative,

        /**
         * 0 to 15 minutes
         */
        UpTo15Minutes,

        /**
         * 15 to 60 minutes
         */
        From15To60Minutes,

        /**
         * 1 to 3 hours
         */
        From1To3Hours,

        /**
         * 3 to 6 hours
         */
        From3To6Hours,

        /**
         * 6 to 12 hours
         */
        From6To12Hours,

        /**
         * 12 to 24 hours
         */
        From12To24Hours,

        /**
         * 24 hours or more
         */
        Over24Hours
    }

    enum class StandbyBucket {
        /**
         * The app is exempt from standby restrictions
         */
        Exempted,

        /**
         * Active bucket
         */
        Active,

        /**
         * Working set bucket
         */
        WorkingSet,

        /**
         * Frequent bucket
         */
        Frequent,

        /**
         * Rare bucket
         */
        Rare,

        /**
         * Restricted bucket
         */
        Restricted,

        /**
         * The bucket is not known or not supported on the device
         */
        Unknown
    }

    enum class LastExit {
        /**
         * The user stopped the previous process: a force stop, a removal from Recents, or a
         * stopped Android user
         */
        UserForceStop,

        /**
         * The previous process ended for another reason
         */
        Other,

        /**
         * The reason is not known or not supported on the device
         */
        Unknown
    }

    enum class NetworkConstraint {
        /**
         * The work needs an unmetered network
         */
        Unmetered,

        /**
         * The work needs any connected network
         */
        Connected,

        /**
         * The work has no network constraint
         */
        None
    }
}
