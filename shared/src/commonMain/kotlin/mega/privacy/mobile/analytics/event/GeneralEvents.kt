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