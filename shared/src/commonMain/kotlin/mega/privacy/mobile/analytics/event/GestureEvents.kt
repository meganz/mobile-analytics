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

@GestureEvent(gesture = "Drag to select started", screen = "Media Screen")
interface MediaScreenDragToSelectStarted

@GestureEvent(gesture = "Vertical swipe to adjust brightness", screen = "Video Player Screen")
interface VideoPlayerBrightnessSwipe

@GestureEvent(gesture = "Vertical swipe to adjust volume", screen = "Video Player Screen")
interface VideoPlayerVolumeSwipe

@GestureEvent(gesture = "Long press for 2x playback speed", screen = "Video Player Screen")
interface VideoPlayerLongPressSpeed

@GestureEvent(gesture = "Double tap to seek forward", screen = "Video Player Screen")
interface VideoPlayerDoubleTapSeekForward

@GestureEvent(gesture = "Double tap to seek backward", screen = "Video Player Screen")
interface VideoPlayerDoubleTapSeekBackward

@GestureEvent(gesture = "Zoom to fill screen", screen = "Video Player Screen")
interface VideoPlayerZoomToFill

@GestureEvent(gesture = "Zoom to fit screen", screen = "Video Player Screen")
interface VideoPlayerZoomToFit

@GestureEvent(gesture = "Pinch to zoom", screen = "Video Player Screen")
interface VideoPlayerPinchToZoom
@GestureEvent(gesture = "Slider drag to change playback speed", screen = "Audio player controller")
interface AudioPlayerSpeedChangeBySlider
