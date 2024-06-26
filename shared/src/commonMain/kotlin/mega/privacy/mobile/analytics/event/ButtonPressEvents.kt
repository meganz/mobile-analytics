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
    buttonName = "Add Items to New Album Button",
    screen = "Select photos screen",
)
interface AddItemsToNewAlbumButton

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

@ButtonPressEvent(
    buttonName = "Album add photos FAB",
    screen = "Album content screen"
)
interface AlbumAddPhotosFAB

@ButtonPressEvent(
    buttonName = "View Pro plans",
    screen = "Onboarding Pro Plan Upselling dialog - Variant A"
)
interface OnboardingUpsellingDialogVariantAViewProPlansButton

@ButtonPressEvent(
    buttonName = "Buy Pro plan",
    screen = "Onboarding Pro Plan Upselling dialog - Variant B"
)
interface OnboardingUpsellingDialogVariantBBuyProPlanButton

@ButtonPressEvent(
    buttonName = "Album import Save to Cloud Drive",
    screen = "Album import screen"
)
interface AlbumImportSaveToCloudDriveButton

@ButtonPressEvent(
    buttonName = "Album import Save to Device",
    screen = "Album import screen"
)
interface AlbumImportSaveToDeviceButton

@ButtonPressEvent(
    buttonName = "Albums storage overquota upgrade account button",
    dialog = "Albums Storage Over Quota"
)
interface AlbumsStorageOverQuotaUpgradeAccountButton

@ButtonPressEvent(
    buttonName = "Transfer overquota upgrade account button",
    dialog = "Transfer Over Quota Dialog"
)
interface  TransferOverQuotaUpgradeAccountButton

@ButtonPressEvent(
    buttonName = "Android Sync Get Started button"
)
interface AndroidSyncGetStartedButton

@ButtonPressEvent(
    buttonName = "Android Sync Start Sync button"
)
interface AndroidSyncStartSyncButton

@ButtonPressEvent(
    buttonName = "Android Sync FAB button"
)
interface AndroidSyncFABButton

@ButtonPressEvent(
    buttonName = "iOS Start conversation button",
    screen = "iOS Home screen"
)
interface IOSStartConversationButton

@ButtonPressEvent(
    buttonName = "iOS Upload files button",
    screen = "iOS Home screen"
)
interface IOSUploadFilesButton

@ButtonPressEvent(
    buttonName = "Device Center entrypoint button",
    screen = "Home screen"
)
interface DeviceCenterEntrypointButton

@ButtonPressEvent(
    buttonName = "Device Center save new device name button",
    screen = "Device Center screen"
)
interface DeviceCenterSaveNewDeviceNameButton

@ButtonPressEvent(
    buttonName = "Device Center device options button",
    screen = "Device Center screen"
)
interface DeviceCenterDeviceOptionsButton

@ButtonPressEvent(
    buttonName = "Add attachment button",
    screen = "Chat Conversation Screen"
)
interface ChatConversationAddAttachmentButtonPressed

@ButtonPressEvent(
    buttonName = "Send image files floating action button",
    screen = "Chat Conversation Screen"
)
interface ChatConversationSendImageFilesFloatingActionButtonPressed

@ButtonPressEvent(
    buttonName = "Onboarding Upselling Dialog Variant B Free Plan Continue button",
    screen = "Onboarding Pro Plan List Screen"
)
interface OnboardingUpsellingDialogVariantBFreePlanContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Onboarding Upselling Dialog Variant B ProI Plan Continue button",
    screen = "Onboarding Pro Plan List Screen"
)
interface OnboardingUpsellingDialogVariantBProIPlanContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Onboarding Upselling Dialog Variant B ProII Plan Continue button",
    screen = "Onboarding Pro Plan List Screen"
)
interface OnboardingUpsellingDialogVariantBProIIPlanContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Onboarding Upselling Dialog Variant B ProIII Plan Continue button",
    screen = "Onboarding Pro Plan List Screen"
)
interface OnboardingUpsellingDialogVariantBProIIIPlanContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Onboarding Upselling Dialog Variant B Pro Lite Plan Continue button",
    screen = "Onboarding Pro Plan List Screen"
)
interface OnboardingUpsellingDialogVariantBProLitePlanContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Sign Up button on USP page",
    screen = "USP Welcome Screen"
)
interface SignUpButtonOnUSPPagePressed

@ButtonPressEvent(
    buttonName = "Sign Up button on Login page",
    screen = "Login Screen"
)
interface SignUpButtonOnLoginPagePressed

@ButtonPressEvent(
    buttonName = "Create Account button on Sign Up page",
    screen = "Sign Up Screen"
)
interface CreateAccountButtonPressed

@ButtonPressEvent(
    buttonName = "Resend Email button on Email Confirmation page",
    screen = "Email Confirmation Screen"
)
interface ResendEmailConfirmationButtonPressed

@ButtonPressEvent(
        buttonName = "Shortcut widget upload file button",
        screen = "Shortcut widget"
)
interface ShortcutWidgetUploadFileButtonPressed

@ButtonPressEvent(
    buttonName = "Shortcut widget scan document button",
    screen = "Shortcut widget"
)
interface ShortcutWidgetScanDocumentButtonPressed

@ButtonPressEvent(
    buttonName = "Shortcut widget start conversation button",
    screen = "Shortcut widget"
)
interface ShortcutWidgetStartConversationButtonPressed

@ButtonPressEvent(
    buttonName = "Shortcut widget add contact button",
    screen = "Shortcut widget"
)
interface ShortcutWidgetAddContactButtonPressed

@ButtonPressEvent(
    buttonName = "Quick access widget recents",
    screen = "Quick access widget"
)
interface QuickAccessWidgetRecentsPressed

@ButtonPressEvent(
    buttonName = "Quick access widget favourites",
    screen = "Quick access widget"
)
interface QuickAccessWidgetFavouritesPressed

@ButtonPressEvent(
    buttonName = "Quick access widget offile",
    screen = "Quick access widget"
)
interface QuickAccessWidgetOffilePressed

@ButtonPressEvent(
    buttonName = "File Type Dropdown Chip",
    screen = "Search Results Screen"
)
interface SearchFileTypeDropdownChipPressed

@ButtonPressEvent(
    buttonName = "Last Modified Dropdown Chip",
    screen = "Search Results Screen"
)
interface SearchLastModifiedDropdownChipPressed

@ButtonPressEvent(
    buttonName = "Date Added Dropdown Chip",
    screen = "Search Results Screen"
)
interface SearchDateAddedDropdownChipPressed

@ButtonPressEvent(
    buttonName = "Subscribe button",
    screen = "Subscription Screen"
)
interface SubscribeButtonPressed

@ButtonPressEvent(
    buttonName = "Subscribe button on Home screen's banner",
    screen = "Home Screen"
)
interface SubscribeButtonHomeBannerPressed

@ButtonPressEvent(
    buttonName = "Subscribe button on Settings screen's banner",
    screen = "Settings Screen"
)
interface SubscribeButtonSettingsBannerPressed

@ButtonPressEvent(
    buttonName = "Connect to VPN toggle button",
    screen = "Home Screen"
)
interface ConnectVPNTogglePressed

@ButtonPressEvent(
    buttonName = "Upgrade button on Sync list screen's empty state",
    screen = "Sync list"
)
interface SyncListEmptyStateUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Upgrade button on Sync list screen's banner",
    screen = "Sync list"
)
interface SyncListBannerUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Upgrade button of the Upgrade dialog on Device Center and Sync list screens",
    screen = "Device center and Sync list"
)
interface SyncFeatureUpgradeDialogUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Cancel button of the Upgrade dialog on Device Center and Sync list screens",
    screen = "Device center and Sync list"
)
interface SyncFeatureUpgradeDialogCancelButtonPressed

@ButtonPressEvent(
    buttonName = "Login button on Login screen",
    screen = "Login Screen"
)
interface LoginButtonPressed

@ButtonPressEvent(
    buttonName = "Close button on Terms of Service screen",
    screen = "Terms of Service Screen"
)
interface TermsOfServiceCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Logout button",
)
interface LogoutButtonPressed

@ButtonPressEvent(
    buttonName = "Upgrade account buy button"
)
interface UpgradeAccountBuyButtonPressed

@ButtonPressEvent(
    buttonName = "Home FAB button",
    screen = "Home Screen"
)
interface HomeFABExpanded

@ButtonPressEvent(
    buttonName = "Home New Chat FAB",
    screen = "Home Screen"
)
interface HomeNewChatFABPressed

@ButtonPressEvent(
    buttonName = "Home Upload FAB",
    screen = "Home Screen"
)
interface HomeUploadFABPressed

@ButtonPressEvent(
    buttonName = "Home FAB button",
    screen = "Home Screen"
)
interface HomeFABClosed

@ButtonPressEvent(
    buttonName = "Start New Conversation FAB",
    screen = "Chat Screen"
)
interface ChatTabFABPressed

@ButtonPressEvent(
    buttonName = "Cloud Drive FAB button",
    screen = "Cloud Drive Screen"
)
interface CloudDriveFABPressed

@ButtonPressEvent(
    buttonName = "Cancel Subscription button"
)
interface CancelSubscriptionButtonPressed

@ButtonPressEvent(
    buttonName = "Cancel Subscription Keep plan button",
    screen = "Cancel Subscription screen"
)
interface CancelSubscriptionKeepPlanButtonPressed

@ButtonPressEvent(
    buttonName = "Cancel Subscription Continue Cancellation button",
    screen = "Cancel Subscription screen"
)
interface CancelSubscriptionContinueCancellationButtonPressed

@ButtonPressEvent(
    buttonName = "Upgrade now",
    screen = "Max Call Duration Reached Modal"
)
interface MaxCallDurationReachedModal

@ButtonPressEvent(
    buttonName = "Upgrade now",
    screen = "Create Meeting"
)
interface CreateMeetingMaxDurationReached

@ButtonPressEvent(
    buttonName = "Upgrade now",
    screen = "Edit Meeting"
)
interface EditMeetingMaxDurationReached

@ButtonPressEvent(
    buttonName = "Upgrade now",
    screen = "Edit Meeting"
)
interface EditSingleOccurrenceMeetingMaxDurationReached

@ButtonPressEvent(
    buttonName = "Raise hand",
    screen = "Call Floating Panel"
)
interface CallRaiseHand

@ButtonPressEvent(
    buttonName = "Lower hand",
    screen = "Call Floating Panel"
)
interface CallLowerHand

@ButtonPressEvent(
    buttonName = "Home FAB button",
    screen = "Home Screen"
)
interface HomeFABPressed

@ButtonPressEvent(
    buttonName = "Home New Chat text",
    screen = "Home Screen"
)
interface HomeNewChatTextPressed

@ButtonPressEvent(
    buttonName = "Home Upload text",
    screen = "Home Screen"
)
interface HomeUploadTextPressed

@ButtonPressEvent(
    buttonName = "Business restrictions banner action button",
)
interface BusinessRestrictionsBannerActionButtonPressed