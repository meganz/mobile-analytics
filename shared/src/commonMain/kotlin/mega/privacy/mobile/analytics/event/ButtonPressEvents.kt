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