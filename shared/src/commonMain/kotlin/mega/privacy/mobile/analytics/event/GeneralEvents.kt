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
interface AccountNotificationCentreEntryDisplayed