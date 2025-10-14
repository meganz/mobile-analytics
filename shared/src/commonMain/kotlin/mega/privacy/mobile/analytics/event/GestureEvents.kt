package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.GestureEvent

@GestureEvent(gesture = "Active transfers swipe to cancel", screen = "Transfers Section Screen")
interface ActiveTransfersSwipeToCancel

@GestureEvent(gesture = "Completed transfers swipe to clear", screen = "Transfers Section Screen")
interface CompletedTransfersSwipeToClear

@GestureEvent(gesture = "Failed transfers swipe to clear", screen = "Transfers Section Screen")
interface FailedTransfersSwipeToClear

@GestureEvent(gesture = "Failed transfers swipe to retry", screen = "Transfers Section Screen")
interface FailedTransfersSwipeToRetry

@GestureEvent(gesture = "Active transfer drag and drop to change priority", screen = "Transfers Section Screen")
interface ActiveTransferDragAndDropToChangePriority