package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.ButtonPressEvent

@ButtonPressEvent(
    buttonName = "DeleteAlbumCancelButton",
    screen = "PhotoScreen",
    dialog = "DeleteAlbumsConfirmationDialog"
)
interface DeleteAlbumCancelButtonPressed

@ButtonPressEvent(
    buttonName = "DeleteAlbumConfirmButton",
    screen = "PhotoScreen",
    dialog = "DeleteAlbumsConfirmationDialog"
)
interface DeleteAlbumConfirmButtonPressed

@ButtonPressEvent(
    buttonName = "Send decryption key separately folder",
)
interface LinkSendDecryptionKeyFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Send decryption key separately file",
)
interface LinkSendDecryptionKeyFileButtonPressed

@ButtonPressEvent(
    buttonName = "Set expiry date for folder"
)
interface LinkSetExpiryDateFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Set expiry date for file"
)
interface LinkSetExpiryDateFileButtonPressed

@ButtonPressEvent(
    buttonName = "Set password for folder"
)
interface LinkSetPasswordFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Set password for file"
)
interface LinkSetPasswordFileButtonPressed

@ButtonPressEvent(
    buttonName = "Confirm password for folder"
)
interface LinkConfirmPasswordFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Confirm password for file"
)
interface LinkConfirmPasswordFileButtonPressed

@ButtonPressEvent(
    buttonName = "Reset password tapped for folder"
)
interface LinkResetPasswordFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Reset password tapped for file"
)
interface LinkResetPasswordFileButtonPressed

@ButtonPressEvent(
    buttonName = "Remove password tapped for folder"
)
interface LinkRemovePasswordFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Remove password tapped for file"
)
interface LinkRemovePasswordFileButtonPressed

@ButtonPressEvent(
    buttonName = "Pro feature see plans tapped for folder",
    dialog = "LinkProFeatureSeePlanFolderDialog"
)
interface LinkProFeatureSeePlanFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Pro feature see plans tapped for file",
    dialog = "LinkProFeatureSeePlanFileDialog"
)
interface LinkProFeatureSeePlanFileButtonPressed

@ButtonPressEvent(
    buttonName = "Pro feature see plans not now tapped for folder",
    dialog = "LinkProFeatureSeeNotNowPlanFolderDialog"
)
interface LinkProFeatureSeeNotNowPlanFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Pro feature see plans not now tapped for file",
    dialog = "LinkProFeatureSeeNotNowPlanFileDialog"
)
interface LinkProFeatureSeeNotNowPlanFileButtonPressed

@ButtonPressEvent(
    buttonName = "Forgot Passcode"
)
interface ForgotPasscodeButtonPressed

@ButtonPressEvent(
    buttonName = "Logout"
)
interface PasscodeLogoutButtonPressed

@ButtonPressEvent(
    buttonName = "InAppUpdate Update"
)
interface InAppUpdateUpdateButtonPressed

@ButtonPressEvent(
    buttonName = "InAppUpdate Cancel"
)
interface InAppUpdateCancelButtonPressed

@ButtonPressEvent(
    buttonName = "InAppUpdate Restart"
)
interface InAppUpdateRestartButtonPressed

@ButtonPressEvent(
    buttonName = "Docs",
    screen = "Home screen"
)
interface HomeScreenDocsTilePressed

@ButtonPressEvent(
    buttonName = "Audio",
    screen = "Home screen"
)
interface HomeScreenAudioTilePressed

@ButtonPressEvent(
    buttonName = "Videos",
    screen = "Home screen"
)
interface HomeScreenVideosTilePressed

@ButtonPressEvent(
    buttonName = "Full screen",
    screen = "Video player controller"
)
interface VideoPlayerFullScreenPressed

@ButtonPressEvent(
    buttonName = "Original",
    screen = "Video player controller"
)
interface VideoPlayerOriginalPressed

@ButtonPressEvent(
    buttonName = "Speed Option 0.5X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface SpeedOption0_5XPressed

@ButtonPressEvent(
    buttonName = "Speed Option 1.5X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface SpeedOption1_5XPressed

@ButtonPressEvent(
    buttonName = "Speed Option 2X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface SpeedOption2XPressed

@ButtonPressEvent(
    buttonName = "Off Option",
    screen = "Video player controller",
    dialog = "AddSubtitleDialog"
)
interface OffOptionForHideSubtitlePressed

@ButtonPressEvent(
    buttonName = "Auto Match Subtitle Option",
    screen = "Video player controller",
    dialog = "AddSubtitleDialog"
)
interface AutoMatchSubtitleOptionPressed

@ButtonPressEvent(
    buttonName = "Add Subtitles Option",
    screen = "Video player controller",
    dialog = "AddSubtitleDialog"
)
interface AddSubtitlesOptionPressed

@ButtonPressEvent(
    buttonName = "Search Mode",
    screen = "SelectSubtitleView"
)
interface SearchModeEnablePressed

@ButtonPressEvent(
    buttonName = "Add Subtitle",
    screen = "SelectSubtitleView"
)
interface AddSubtitlePressed

@ButtonPressEvent(
    buttonName = "Cancel Select Subtitle",
    screen = "SelectSubtitleView"
)
interface CancelSelectSubtitlePressed

@ButtonPressEvent(
    buttonName = "Loop Button",
    screen = "Video player controller"
)
interface LoopButtonPressed

@ButtonPressEvent(
    buttonName = "Lock Button",
    screen = "Video player controller"
)
interface LockButtonPressed

@ButtonPressEvent(
    buttonName = "Unlock Button",
    screen = "Video player controller"
)
interface UnlockButtonPressed

@ButtonPressEvent(
    buttonName = "Snapshot Button",
    screen = "Video player controller"
)
interface SnapshotButtonPressed

@ButtonPressEvent(
    buttonName = "Create Album Dialog Button",
    screen = "Photos screen album tab",
)
interface CreateAlbumDialogButtonPressed

@ButtonPressEvent(
    buttonName = "Add Items to New Album FAB",
    screen = "Select photos screen",
)
interface AddItemsToNewAlbumFAB

@ButtonPressEvent(
    buttonName = "Add Items to Existing Album FAB",
    screen = "Select photos screen",
)
interface AddItemsToExistingAlbumFAB

@ButtonPressEvent(
    buttonName = "Remove Items from Album Button",
    screen = "Album content screen",
)
interface RemoveItemsFromAlbumDialogButton

@ButtonPressEvent(
    buttonName = "Slideshow preference repeat ON toggle",
    screen = "Slideshow settings screen"
)
class SlideshowSettingRepeatOnButton

@ButtonPressEvent(
    buttonName = "Slideshow preference repeat OFF toggle",
    screen = "Slideshow settings screen"
)
class SlideshowSettingRepeatOffButton

@ButtonPressEvent(
    buttonName = "Slideshow preference speed slow 8s",
    screen = "Slideshow settings screen",
    dialog = "SlideshowSpeedDialog"
)
interface SlideshowSettingSpeedSlowButton

@ButtonPressEvent(
    buttonName = "Slideshow preference speed normal 4s",
    screen = "Slideshow settings screen",
    dialog = "SlideshowSpeedDialog"
)
interface SlideshowSettingSpeedNormalButton

@ButtonPressEvent(
    buttonName = "Slideshow preference speed fast 2s",
    screen = "Slideshow settings screen",
    dialog = "SlideshowSpeedDialog"
)
interface SlideshowSettingSpeedFastButton

@ButtonPressEvent(
    buttonName = "Slideshow preference order shuffle",
    screen = "Slideshow settings screen",
    dialog = "SlideshowOrderDialog"
)
interface SlideshowSettingOrderShuffleButton

@ButtonPressEvent(
    buttonName = "Slideshow preference order newest",
    screen = "Slideshow settings screen",
    dialog = "SlideshowOrderDialog"
)
interface SlideshowSettingOrderNewestButton

@ButtonPressEvent(
    buttonName = "Slideshow preference order oldest",
    screen = "Slideshow settings screen",
    dialog = "SlideshowOrderDialog"
)
interface SlideshowSettingOrderOldestButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting recurrence option",
    screen = "Scheduled meeting options screen"
)
interface ScheduledMeetingSettingRecurrenceButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting enable meeting link option",
    screen = "Scheduled meeting options screen"
)
interface ScheduledMeetingSettingEnableMeetingLinkButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting enable send calendar invite option",
    screen = "Scheduled meeting options screen"
)
interface ScheduledMeetingSettingSendCalendarInviteButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting create confirmation button",
    screen = "Scheduled meeting options screen"
)
interface ScheduledMeetingCreateConfirmButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting share meeting link button",
    screen = "Scheduled meeting info screen"
)
interface ScheduledMeetingShareMeetingLinkButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting reminder notification join button",
    dialog = "Scheduled meeting reminder notification"
)
interface ScheduledMeetingReminderNotificationJoinButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting reminder notification message button",
    dialog = "Scheduled meeting reminder notification"
)
interface ScheduledMeetingReminderNotificationMessageButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting enable open invite option (allow non-hosts to add participants)",
    screen = "Scheduled meeting options and info screens"
)
interface ScheduledMeetingSettingEnableOpenInviteButton

@ButtonPressEvent(
    buttonName = "Waiting room enable option",
    screen = "Scheduled meeting options and info screens"
)
interface WaitingRoomEnableButton

@ButtonPressEvent(
    buttonName = "Waiting room leave button",
    screen = "Waiting room screen"
)
interface WaitingRoomLeaveButton

@ButtonPressEvent(
    buttonName = "Scheduled meeting join as a guest button",
    screen = "Join as a guest screen"
)
interface ScheduledMeetingJoinGuestButton

@ButtonPressEvent(
    buttonName = "Create album FAB",
    screen = "Photos screen albums tab"
)
interface CreateAlbumFAB

@ButtonPressEvent(
    buttonName = "Album photos selection filter all locations",
    screen = "Album photos selection screen",
    dialog = "Album photos selection select location dialog"
)
interface AlbumPhotosSelectionAllLocationsButton

@ButtonPressEvent(
    buttonName = "Album photos selection filter cloud drive",
    screen = "Album photos selection screen",
    dialog = "Album photos selection select location dialog"
)
interface AlbumPhotosSelectionCloudDriveButton

@ButtonPressEvent(
    buttonName = "Album photos selection filter camera uploads",
    screen = "Album photos selection screen",
    dialog = "Album photos selection select location dialog"
)
interface AlbumPhotosSelectionCameraUploadsButton
