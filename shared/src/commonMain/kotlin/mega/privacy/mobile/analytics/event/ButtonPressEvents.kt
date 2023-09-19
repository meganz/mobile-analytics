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
