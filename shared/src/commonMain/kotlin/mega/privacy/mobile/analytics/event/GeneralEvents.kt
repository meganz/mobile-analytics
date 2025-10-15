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