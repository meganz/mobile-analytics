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
    buttonName = "Speed Option 0.25X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface VideoSpeedOptionPressed_0_25X

@ButtonPressEvent(
    buttonName = "Speed Option 0.5X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface SpeedOption0_5XPressed

@ButtonPressEvent(
    buttonName = "Speed Option 0.75X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface VideoSpeedOptionPressed_0_75X

@ButtonPressEvent(
    buttonName = "Speed Option 1X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface VideoSpeedOptionPressed_1X

@ButtonPressEvent(
    buttonName = "Speed Option 1.25X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface VideoSpeedOptionPressed_1_25X

@ButtonPressEvent(
    buttonName = "Speed Option 1.5X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface SpeedOption1_5XPressed

@ButtonPressEvent(
    buttonName = "Speed Option 1.75X",
    screen = "Video player controller",
    dialog = "SpeedSelectedPopup"
)
interface VideoSpeedOptionPressed_1_75X

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
interface TransferOverQuotaUpgradeAccountButton

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
    buttonName = "Subscription Cancellation Survey Cancel View button",
    screen = "Subscription Cancellation Survey screen"
)
interface SubscriptionCancellationSurveyCancelViewButton

@ButtonPressEvent(
    buttonName = "Subscription Cancellation Survey Cancel Subscription button",
    screen = "Subscription Cancellation Survey screen"
)
interface SubscriptionCancellationSurveyCancelSubscriptionButton

@ButtonPressEvent(
    buttonName = "Subscription Cancellation Survey Don't Cancel button",
    screen = "Subscription Cancellation Survey screen"
)
interface SubscriptionCancellationSurveyDontCancelButton

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

@ButtonPressEvent(
    buttonName = "Upgrade",
    screen = "My Account Hall"
)
interface UpgradeMyAccount

@ButtonPressEvent(
    buttonName = "Cancel upgrade",
    screen = "Upgrade Account Plan"
)
interface CancelUpgradeMyAccount

@ButtonPressEvent(
    buttonName = "Buy Pro Lite",
    screen = "Upgrade Account Plan"
)
interface BuyProLite

@ButtonPressEvent(
    buttonName = "Buy Pro I",
    screen = "Upgrade Account Plan"
)
interface BuyProI

@ButtonPressEvent(
    buttonName = "Buy Pro II",
    screen = "Upgrade Account Plan"
)
interface BuyProII

@ButtonPressEvent(
    buttonName = "Buy Pro III",
    screen = "Upgrade Account Plan"
)
interface BuyProIII

@ButtonPressEvent(
    buttonName = "Upgrade",
    screen = "Hidden Node Upgrade Screen"
)
interface HiddenNodeUpgradeUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Close",
    screen = "Hidden Node Upgrade Screen"
)
interface HiddenNodeUpgradeCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Continue",
    screen = "Hidden Node Onboarding Screen"
)
interface HiddenNodeOnboardingContinueButtonPressed

@ButtonPressEvent(
    buttonName = "Close",
    screen = "Hidden Node Onboarding Screen"
)
interface HiddenNodeOnboardingCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Share Link Bar Button",
    screen = "Call UI - Navigation Bar"
)
interface ShareLinkBarButtonPressed

@ButtonPressEvent(
    buttonName = "Send Link To Chat",
    screen = "Call UI - Share link alert"
)
interface SendLinkToChatPressed

@ButtonPressEvent(
    buttonName = "Copy Link To Pasteboard",
    screen = "Call UI - Share link alert"
)
interface CopyLinkToPasteboardPressed

@ButtonPressEvent(
    buttonName = "Share Link",
    screen = "Call UI - Share link alert"
)
interface ShareLinkPressed

@ButtonPressEvent(
    buttonName = "Invite participants",
    screen = "Call UI - Share link alert"
)
interface InviteParticipantsPressed

@ButtonPressEvent(
    buttonName = "More",
    screen = "CallScreen"
)
interface CallUIMoreButtonPressed

@ButtonPressEvent(
    buttonName = "Invite Participants",
    screen = "CallScreen"
)
interface ParticipantListInviteParticipantRowPressed

@ButtonPressEvent(
    buttonName = "Share meeting link",
    screen = "CallScreen"
)
interface ParticipantListShareMeetingLinkPressed

@ButtonPressEvent(
    buttonName = "Learn more invite contacts ",
    screen = "Chat Screen"
)
interface InviteFriendsLearnMorePressed

@ButtonPressEvent(
    buttonName = "Invite friends",
    screen = "Chat Screen"
)
interface InviteFriendsPressed

@ButtonPressEvent(
    buttonName = "Invite contacts",
    screen = "New Chat Screen"
)
interface InviteContactsPressed

@ButtonPressEvent(
    buttonName = "Invite to MEGA",
    screen = "New Chat Screen"
)
interface InviteToMEGAPressed

@ButtonPressEvent(
    buttonName = "Group Chat",
    screen = "New Chat Screen"
)
interface GroupChatPressed

@ButtonPressEvent(
    buttonName = "Start Meeting Now",
    screen = "Meetings Landing Screen"
)
interface StartMeetingNowPressed

@ButtonPressEvent(
    buttonName = "Join Meeting",
    screen = "Meetings Landing Screen"
)
interface JoinMeetingPressed

@ButtonPressEvent(
    buttonName = "Schedule Meeting",
    screen = "Meetings Landing Screen"
)
interface ScheduleMeetingPressed

@ButtonPressEvent(
    buttonName = "Widget button to connect to the VPN"
)
interface WidgetConnectVPNButtonPressed

@ButtonPressEvent(
    buttonName = "Widget button to disconnect from the VPN"
)
interface WidgetDisconnectVPNButtonPressed

@ButtonPressEvent(
    buttonName = "Full Storage Upgrade Now Button"
)
interface FullStorageOverQuotaBannerUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Almost Full Storage Upgrade Now Button"
)
interface AlmostFullStorageOverQuotaBannerUpgradeButtonPressed

@ButtonPressEvent(
    buttonName = "Almost Full Storage Close Banner Button"
)
interface AlmostFullStorageOverQuotaBannerCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Scan QR code Button",
    screen = "Invite Contact"
)
interface ScanQRCodeButtonPressed

@ButtonPressEvent(
    buttonName = "Invite contacts Button",
    screen = "Invite Contact"
)
interface InviteContactsButtonPressed

@ButtonPressEvent(
    buttonName = "Start your free trial"
)
interface StartFreeTrialButtonPressed

@ButtonPressEvent(
    buttonName = "Add new password item",
    screen = "Home Screen"
)
interface AddNewPasswordItemButtonPressed

@ButtonPressEvent(
    buttonName = "Copy username",
    screen = "Item Detail"
)
interface CopyUserNameButtonPressed

@ButtonPressEvent(
    buttonName = "View password",
    screen = "Item Detail"
)
interface ViewPasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Copy password",
    screen = "Item Detail"
)
interface CopyPasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Launch WebSite",
    screen = "Item Detail"
)
interface LaunchWebSiteButtonPressed

@ButtonPressEvent(
    buttonName = "Copy notes",
    screen = "Item Detail"
)
interface CopyNotesButtonPressed

@ButtonPressEvent(
    buttonName = "Generate password",
    screen = "Add Item"
)
interface GeneratePasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Copy password",
    screen = "Generate Password Widget Screen"
)
interface GeneratePasswordCopyPasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Use Capital letter ON toggle",
    screen = "Generate Password Widget Screen"
)
interface UseCapitalLettersOnTogglePressed

@ButtonPressEvent(
    buttonName = "Use Capital letter OFF toggle",
    screen = "Generate Password Widget Screen"
)
interface UseCapitalLettersOffTogglePressed

@ButtonPressEvent(
    buttonName = "Use Digits ON toggle",
    screen = "Generate Password Widget Screen"
)
interface UseDigitsOnTogglePressed

@ButtonPressEvent(
    buttonName = "Use Digits OFF toggle",
    screen = "Generate Password Widget Screen"
)
interface UseDigitsOffTogglePressed

@ButtonPressEvent(
    buttonName = "Use Symbols ON toggle",
    screen = "Generate Password Widget Screen"
)
interface UseSymbolsOnTogglePressed

@ButtonPressEvent(
    buttonName = "Use Symbols OFF toggle",
    screen = "Generate Password Widget Screen"
)
interface UseSymbolsOffTogglePressed

@ButtonPressEvent(
    buttonName = "Use password",
    screen = "Generate Password Widget Screen"
)
interface UsePasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Regenerate",
    screen = "Generate Password Widget Screen"
)
interface RegenerateButtonPressed

@ButtonPressEvent(
    buttonName = "Turn on autofill toggle",
    screen = "Settings Screen"
)
interface AutofillToggleEnabled

@ButtonPressEvent(
    buttonName = "Turn off autofill toggle",
    screen = "Settings Screen"
)
interface AutofillToggleDisabled

@ButtonPressEvent(
    buttonName = "Sync folders button of the Sync Promotion Bottom Sheet",
    screen = "Sync Promotion Bottom Sheet"
)
interface SyncPromotionBottomSheetSyncFoldersButtonPressed

@ButtonPressEvent(
    buttonName = "Learn more button of the Sync Promotion Bottom Sheet",
    screen = "Sync Promotion Bottom Sheet"
)
interface SyncPromotionBottomSheetLearnMoreButtonPressed

@ButtonPressEvent(
    buttonName = "Meeting link enabled on edit scheduled meeting",
    screen = "Edit Scheduled Meeting"
)
interface EditScheduledMeetingSettingEnableMeetingLinkButton

@ButtonPressEvent(
    buttonName = "Meeting link enabled on edit scheduled meeting occurrence",
    screen = "Edit Scheduled Meeting"
)
interface EditScheduledMeetingOccurrenceSettingEnableMeetingLinkButton

@ButtonPressEvent(
    buttonName = "Send meeting link to chat",
    screen = "Share link dialog"
)
interface SendMeetingLinkToChatScheduledMeeting

@ButtonPressEvent(
    buttonName = "Share meeting link",
    screen = "Share link dialog"
)
interface ShareMeetingLinkScheduledMeeting

@ButtonPressEvent(
    buttonName = "Back up Recovery key",
    screen = "Profile screen"
)
interface BackupRecoveryKeyButtonPressed

@ButtonPressEvent(
    buttonName = "Copy",
    screen = "Recovery key screen"
)
interface RecoveryKeyCopyButtonPressed

@ButtonPressEvent(
    buttonName = "OK",
    screen = "Recovery key screen's copy dialog"
)
interface RecoveryKeyCopyOkButtonPressed

@ButtonPressEvent(
    buttonName = "Save",
    screen = "Recovery key screen"
)
interface RecoveryKeySaveButtonPressed

@ButtonPressEvent(
    buttonName = "OK",
    screen = "Recovery key screen's save dialog"
)
interface RecoveryKeySaveOkButtonPressed

@ButtonPressEvent(
    buttonName = "Why do I need a Recovery key",
    screen = "Recovery key screen"
)
interface RecoveryKeyWhyDoINeedARecoveryKeyButtonPressed

@ButtonPressEvent(
    buttonName = "Close",
    screen = "Password reminder screen"
)
interface PasswordReminderCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Test Password",
    screen = "Password reminder screen"
)
interface PasswordReminderTestPasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Proceed to log out",
    screen = "Password reminder screen"
)
interface PasswordReminderProceedToLogoutButtonPressed

@ButtonPressEvent(
    buttonName = "Export recovery key",
    screen = "Password reminder screen"
)
interface PasswordReminderExportRecoveryKeyButtonPressed

@ButtonPressEvent(
    buttonName = "OK",
    screen = "Password reminder screen's export recovery key dialog"
)
interface PasswordReminderExportRecoveryKeyOkButtonPressed

@ButtonPressEvent(
    buttonName = "Confirm",
    screen = "Test Password screen"
)
interface TestPasswordConfirmButtonPressed

@ButtonPressEvent(
    buttonName = "Export recovery key",
    screen = "Test Password screen"
)
interface TestPasswordExportRecoveryKeyButtonPressed

@ButtonPressEvent(
    buttonName = "Proceed to log out",
    screen = "Test Password screen"
)
interface TestPasswordProceedToLogoutButtonPressed

@ButtonPressEvent(
    buttonName = "OK",
    screen = "Test Password screen's export recovery key dialog"
)
interface TestPasswordExportRecoveryKeyOkButtonPressed

@ButtonPressEvent(
    buttonName = "Kill Switch",
    screen = "Settings screen"
)
interface KillSwitchToggleEnabledPressed

@ButtonPressEvent(
    buttonName = "Kill Switch",
    screen = "Settings screen"
)
interface KillSwitchToggleDisabledPressed

@ButtonPressEvent(
    buttonName = "Kill Switch",
    screen = "Notification"
)
interface KillSwitchNotificationDisabledPressed

@ButtonPressEvent(
    buttonName = "Notifications",
    screen = "My Account Hall"
)
interface NotificationsEntryButtonPressed

@ButtonPressEvent(
    buttonName = "Add Participant",
    screen = "Scheduled meeting info screen"
)
interface MeetingInfoAddParticipantButtonTapped

@ButtonPressEvent(
    buttonName = "Leave meeting",
    screen = "Scheduled meeting info screen"
)
interface MeetingInfoLeaveMeetingButtonTapped

@ButtonPressEvent(
    buttonName = "Audio player queue",
    screen = "Audio player controller"
)
interface AudioPlayerQueueButtonPressed

@ButtonPressEvent(
    buttonName = "Android Sync Multi FAB Button",
    screen = "Sync list screen"
)
interface AndroidSyncMultiFABButtonPressed

@ButtonPressEvent(
    buttonName = "Android Backup FAB Button",
    screen = "Sync list screen"
)
interface AndroidBackupFABButtonPressed

@ButtonPressEvent(
    buttonName = "Android Sync select device folder button",
    screen = "Add new sync screen"
)
interface AndroidSyncSelectDeviceFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Android Sync all files access permission dialog confirm button",
    screen = "Sync List and New Sync screens"
)
interface AndroidSyncAllFilesAccessDialogConfirmButtonPressed

@ButtonPressEvent(
    buttonName = "Android Sync all files access permission dialog dismiss button",
    screen = "Sync List and New Sync screens"
)
interface AndroidSyncAllFilesAccessDialogDismissButtonPressed

@ButtonPressEvent(
    buttonName = "Sync card open device folder button",
    screen = "Sync Card and Sync List"
)
interface SyncCardOpenDeviceFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Sync card open MEGA folder button",
    screen = "Sync Card and Sync List"
)
interface SyncCardOpenMegaFolderButtonPressed

@ButtonPressEvent(
    buttonName = "Sync card pause/run button",
    screen = "Sync Card and Sync List"
)
interface SyncCardPauseRunButtonPressed

@ButtonPressEvent(
    buttonName = "Sync card stop button",
    screen = "Sync Card and Sync List"
)
interface SyncCardStopButtonPressed

@ButtonPressEvent(
    buttonName = "Sync card issues info button",
    screen = "Sync Card and Sync List"
)
interface SyncCardIssuesInfoButtonPressed

@ButtonPressEvent(
    buttonName = "Text editor edit button",
    screen = "Text Editor Screen"
)
interface TextEditorEditButtonPressed

@ButtonPressEvent(
    buttonName = "Turn on autolock toggle",
    screen = "Settings Screen"
)
interface AutolockToggleEnabled

@ButtonPressEvent(
    buttonName = "Turn off autolock toggle",
    screen = "Settings Screen"
)
interface AutolockToggleDisabled

@ButtonPressEvent(
    buttonName = "Video playlist creation button",
    screen = "Video Section Screen"
)
interface VideoPlaylistCreationButtonPressed

@ButtonPressEvent(
    buttonName = "Open recently watched video button",
    screen = "Video Section Screen"
)
interface RecentlyWatchedOpenedButtonPressed

@ButtonPressEvent(
    buttonName = "Search button",
    screen = "Video Section Screen"
)
interface VideoSectionSearchButtonPressed

@ButtonPressEvent(
    buttonName = "Close Ads button",
    screen = "Ads banner view"
)
interface AdsBannerCloseAdsButtonPressed

@ButtonPressEvent(
    buttonName = "View pro plans",
    screen = "Ad-free dialog screen"
)
interface AdFreeDialogScreenViewProPlansButtonPressed

@ButtonPressEvent(
    buttonName = "Skip",
    screen = "Ad-free dialog screen"
)
interface AdFreeDialogScreenSkipButtonPressed

@ButtonPressEvent(
    buttonName = "Buy plan",
    screen = "Ad-free dialog screen's upgrade plan page"
)
interface AdFreeDialogUpgradeAccountPlanPageBuyButtonPressed

@ButtonPressEvent(
    buttonName = "Buy plan",
    screen = "Upgrade account plan page"
)
interface AdsUpgradeAccountPlanPageBuyButtonPressed

@ButtonPressEvent(
    buttonName = "Auto-connect VPN Toggle",
    screen = "Auto-connect VPN page"
)
interface AutoConnectVPNToggleEnablePressed

@ButtonPressEvent(
    buttonName = "Auto-connect VPN Toggle",
    screen = "Auto-connect VPN page"
)
interface AutoConnectVPNToggleDisablePressed

@ButtonPressEvent(
    buttonName = "Add Trusted Network",
    screen = "Auto-connect VPN page"
)
interface AddTrustedNetworkButtonPressed

@ButtonPressEvent(
    buttonName = "Remove Trusted Network",
    screen = "Auto-connect VPN page"
)
interface RemoveTrustedNetworkButtonPressed

@ButtonPressEvent(
    buttonName = "Skip",
    screen = "Cancellation Survey"
)
interface SkipCancellationSurveyButtonPressed

@ButtonPressEvent(
    buttonName = "Not now",
    screen = "Onboarding Initial Page Screen"
)
interface OnboardingInitialPageNotNowButtonPressed

@ButtonPressEvent(
    buttonName = "Set up MEGA",
    screen = "Onboarding Initial Page Screen"
)
interface OnboardingInitialPageSetUpMegaButtonPressed

@ButtonPressEvent(
    buttonName = "Skip set up",
    screen = "Initial launch Page Screen"
)
interface InitialLaunchSkipSetUpButtonPressed

@ButtonPressEvent(
    buttonName = "Set up MEGA",
    screen = "Initial launch Page Screen"
)
interface InitialLaunchSetUpButtonPressed

@ButtonPressEvent(
    buttonName = "Help",
    screen = "Login Page Screen"
)
interface LoginHelpButtonPressed

@ButtonPressEvent(
    buttonName = "Pause",
    screen = "Home Screen"
)
interface PauseVpnButtonPressed

@ButtonPressEvent(
    buttonName = "Resume",
    screen = "Home Screen"
)
interface ResumeVpnButtonPressed

@ButtonPressEvent(
    buttonName = "Begin network test",
    screen = "Network Test Screen"
)
interface BeginNetworkTestButtonPressed

@ButtonPressEvent(
    buttonName = "Learn more",
    screen = "Network Test Screen"
)
interface NetworkErrorLearnMoreButtonPressed

@ButtonPressEvent(
    buttonName = "Back up folders button of the Sync Promotion Bottom Sheet",
    screen = "Sync Promotion Bottom Sheet"
)
interface SyncPromotionBottomSheetBackUpFoldersButtonPressed

@ButtonPressEvent(
    buttonName = "Promotional Sheet Primary Button",
    screen = "Promotional Sheet"
)
interface PromotionalSheetPrimaryButtonPressed

@ButtonPressEvent(
    buttonName = "Promotional Sheet Secondary Button",
    screen = "Promotional Sheet"
)
interface PromotionalSheetSecondaryButtonPressed

@ButtonPressEvent(
    buttonName = "Hide Node Info Button"
)
interface HideNodeInfoButtonPressed

@ButtonPressEvent(
    buttonName = "Submit",
    screen = "Debug Logs Screen"
)
interface SubmitDebugLogsButtonPressed

@ButtonPressEvent(
    buttonName = "Folders",
    screen = "Sync List Screen"
)
interface SyncListFoldersButtonPressed

@ButtonPressEvent(
    buttonName = "Issues",
    screen = "Sync List Screen"
)
interface SyncListIssuesButtonPressed

@ButtonPressEvent(
    buttonName = "Solved Issues",
    screen = "Sync List Screen"
)
interface SyncListSolvedIssuesButtonPressed

@ButtonPressEvent(
    buttonName = "Allow LAN Connections",
    screen = "Settings Screen"
)
interface AllowLANConnectionsToggleEnabledPressed

@ButtonPressEvent(
    buttonName = "Allow LAN Connections",
    screen = "Settings Screen"
)
interface AllowLANConnectionsToggleDisabledPressed

@ButtonPressEvent(
    buttonName = "Send to chat folder link no account logged",
    screen = "Folder link"
)
interface SendToChatFolderLinkNoAccountLoggedButtonPressed

@ButtonPressEvent(
    buttonName = "Send to chat folder link",
    screen = "Folder link"
)
interface SendToChatFolderLinkButtonPressed

@ButtonPressEvent(
    buttonName = "Send to chat file link no account logged",
    screen = "File link"
)
interface SendToChatFileLinkNoAccountLoggedButtonPressed

@ButtonPressEvent(
    buttonName = "Send to chat file link",
    screen = "File link"
)
interface SendToChatFileLinkButtonPressed

@ButtonPressEvent(
    buttonName = "Archive note to self"
)
interface ArchiveNoteToSelfButtonPressed

@ButtonPressEvent(
    buttonName = "Create note to self",
    screen = "New chat"
)
interface CreateNoteToSelfButtonPressed

@ButtonPressEvent(
    buttonName = "Open note to self",
    screen = "Chats list"
)
interface OpenNoteToSelfButtonPressed

@ButtonPressEvent(
    buttonName = "Forgot password",
    screen = "Login screen"
)
interface ForgotPasswordButtonPressed

@ButtonPressEvent(
    buttonName = "Change email address",
    screen = "Login screen"
)
interface ChangeEmailAddressButtonPressed

@ButtonPressEvent(
    buttonName = "Maybe later",
    screen = "Upgrade Account Plan Screen"
)
interface MaybeLaterUpgradeAccountButtonPressed

@ButtonPressEvent(
    buttonName = "Get started for free",
    screen = "Upgrade Account Plan Screen"
)
interface GetStartedForFreeUpgradePlanButtonPressed

@ButtonPressEvent(
    buttonName = "Enable notifications",
    screen = "Notifications CTA Screen"
)
interface EnableNotificationsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Skip for now on Notifications CTA screen",
    screen = "Notifications CTA Screen"
)
interface SkipNotificationsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Don't allow button on notifications permission dialog",
    screen = "Notifications CTA Screen"
)
interface DontAllowNotificationsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Allow button on notifications permission dialog",
    screen = "Notifications CTA Screen"
)
interface AllowNotificationsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Enable camera uploads",
    screen = "Camera Backups CTA Screen"
)
interface EnableCameraBackupsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Skip for now on Camera Backups CTA screen",
    screen = "Camera Backups CTA Screen"
)
interface SkipCameraBackupsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Limited Access button on Photos permission dialog",
    screen = "Camera Backups CTA Screen"
)
interface LimitedAccessCameraBackupsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Allow Full Access button on Photos permission dialog",
    screen = "Camera Backups CTA Screen"
)
interface FullAccessCameraBackupsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Don't allow button on Photos permission dialog",
    screen = "Camera Backups CTA Screen"
)
interface DontAllowCameraBackupsCTAButtonPressed

@ButtonPressEvent(
    buttonName = "Add new credit card item",
    screen = "Home Screen"
)
interface AddNewCreditCardItemButtonPressed

@ButtonPressEvent(
    buttonName = "Save add credit card item",
    screen = "Add Credit Card Item Screen"
)
interface SaveAddCreditCardItemButtonPressed

@ButtonPressEvent(
    buttonName = "Save edit credit card item",
    screen = "Edit Credit Card Item Screen"
)
interface SaveEditCreditCardItemButtonPressed

@ButtonPressEvent(
    buttonName = "Close add credit card",
    screen = "Add Credit Card Item Screen"
)
interface AddCreditCardCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Close edit credit card",
    screen = "Edit Credit Card Item Screen"
)
interface EditCreditCardCloseButtonPressed

@ButtonPressEvent(
    buttonName = "Discard add credit card dialog keep editing button",
    screen = "Add Credit Card Item Screen"
)
interface DiscardAddCreditCardDialogKeepEditingButtonPressed

@ButtonPressEvent(
    buttonName = "Discard edit credit card dialog keep editing button",
    screen = "Edit Credit Card Item Screen"
)
interface DiscardEditCreditCardDialogKeepEditingButtonPressed

@ButtonPressEvent(
    buttonName = "Discard add credit card dialog discard button",
    screen = "Add Credit Card Item Screen"
)
interface DiscardAddCreditCardDialogDiscardButtonPressed

@ButtonPressEvent(
    buttonName = "Discard edit credit card dialog discard button",
    screen = "Edit Credit Card Item Screen"
)
interface DiscardEditCreditCardDialogDiscardButtonPressed

@ButtonPressEvent(
    buttonName = "Edit credit card button",
    screen = "Credit Card Detail Screen"
)
interface CreditCardDetailEditButtonPressed

@ButtonPressEvent(
    buttonName = "Delete credit card button",
    screen = "Credit Card Detail Screen"
)
interface CreditCardDetailDeleteButtonPressed

@ButtonPressEvent(
    buttonName = "Copy cardholder name button",
    screen = "Credit Card Detail Screen "
)
interface CopyCardholderNameButtonPressed

@ButtonPressEvent(
    buttonName = "Copy card number button",
    screen = "Credit Card Detail Screen "
)
interface CopyCardNumberButtonPressed

@ButtonPressEvent(
    buttonName = "Copy expiration date button",
    screen = "Credit Card Detail Screen "
)
interface CopyExpirationDateButtonPressed

@ButtonPressEvent(
    buttonName = "Copy CVV button",
    screen = "Credit Card Detail Screen "
)
interface CopyCVVButtonPressed

@ButtonPressEvent(
    buttonName = "Delete credit card dialog cancel button",
    screen = "Credit Card Detail Screen "
)
interface DeleteCreditCardDialogCancelButtonPressed

@ButtonPressEvent(
    buttonName = "Delete credit card dialog delete button",
    screen = "Credit Card Detail Screen "
)
interface DeleteCreditCardDialogDeleteButtonPressed

@ButtonPressEvent(
    buttonName = "Active transfers individual play button",
    screen = "Transfers Section Screen"
)
interface ActiveTransfersIndividualPlayButtonButtonPressed

@ButtonPressEvent(
    buttonName = "Active transfers individual pause button",
    screen = "Transfers Section Screen"
)
interface ActiveTransfersIndividualPauseButtonButtonPressed

@ButtonPressEvent(
    buttonName = "Spotlight search node button",
    screen = "Spotlight search"
)
interface SpotlightNodeButtonPressed

@ButtonPressEvent(
    buttonName = "Enable Ad-blocking",
    screen = "Ad-blocking Onboarding Screen"
)
interface EnableAdBlockingOnboardingButtonPressed

@ButtonPressEvent(
    buttonName = "Skip Ad-blocking",
    screen = "Ad-blocking Onboarding Screen"
)
interface SkipAdBlockingOnboardingButtonPressed

@ButtonPressEvent(
    buttonName = "Continue setup VPN",
    screen = "VPN Setup Onboarding Screen"
)
interface ContinueSetupVPNOnboardingButtonPressed

@ButtonPressEvent(
    buttonName = "Skip setup VPN",
    screen = "VPN Setup Onboarding Screen"
)
interface SkipSetupVPNOnboardingButtonPressed

@ButtonPressEvent(
    buttonName = "Continue setup Notification",
    screen = "Notification Setup Onboarding Screen",
)
interface ContinueSetupNotificationOnboardingButtonPressed

@ButtonPressEvent(
    buttonName = "Skip setup Notification",
    screen = "Notification Setup Onboarding Screen",
)
interface SkipSetupNotificationOnboardingButtonPressed