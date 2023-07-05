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

