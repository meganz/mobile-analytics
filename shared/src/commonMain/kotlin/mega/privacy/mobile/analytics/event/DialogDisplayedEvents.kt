package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent

@DialogDisplayedEvent(dialog = "DeleteAlbumsConfirmationDialog", screen = "PhotoScreen")
interface DeleteAlbumsConfirmationDialog

@DialogDisplayedEvent(dialog = "RemoveLinksConfirmationDialog", screen = "PhotoScreen")
interface RemoveLinksConfirmationDialog

@DialogDisplayedEvent(dialog = "CreateNewAlbumDialog", screen = "PhotoScreen")
interface CreateNewAlbumDialog

@DialogDisplayedEvent(dialog = "LinkProFeatureSeePlanFolderDialog")
interface LinkProFeatureSeePlanFolderDialog

@DialogDisplayedEvent(dialog = "LinkProFeatureSeePlanFileDialog")
interface LinkProFeatureSeePlanFileDialog

@DialogDisplayedEvent(dialog = "LinkProFeatureSeeNotNowPlanFolderDialog")
interface LinkProFeatureSeeNotNowPlanFolderDialog

@DialogDisplayedEvent(dialog = "LinkProFeatureSeeNotNowPlanFileDialog")
interface LinkProFeatureSeeNotNowPlanFileDialog

@DialogDisplayedEvent(dialog = "AlbumImportInputDecryptionKeyDialog", screen = "AlbumImportScreen")
interface AlbumImportInputDecryptionKeyDialog

@DialogDisplayedEvent(dialog = "PasscodeUnlockDialog")
interface PasscodeUnlockDialog

@DialogDisplayedEvent(dialog = "PasscodeBiometricUnlockDialog")
interface PasscodeBiometricUnlockDialog

@DialogDisplayedEvent(dialog = "SpeedSelectedDialog", screen = "SpeedSelectedPopup")
interface SpeedSelectedDialog

@DialogDisplayedEvent(dialog = "AddSubtitleDialog", screen = "AddSubtitleDialog")
interface AddSubtitleDialog

@DialogDisplayedEvent(dialog = "AlbumImportStorageOverQuota", screen = "AlbumImportScreen")
interface AlbumImportStorageOverQuotaDialog

@DialogDisplayedEvent(dialog = "TransferOverQuotaWarningDialog")
interface TransferOverQuotaDialog