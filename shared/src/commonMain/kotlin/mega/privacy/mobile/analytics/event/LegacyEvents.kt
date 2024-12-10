package mega.privacy.mobile.analytics.event
import mega.privacy.mobile.analytics.annotations.LegacyEvent

@LegacyEvent(eventId = 99309)
interface EndCallForAll

@LegacyEvent(eventId = 99311)
interface EndCallInNoParticipantsPopup

@LegacyEvent(eventId = 99313)
interface StayOnCallInNoParticipantsPopup

@LegacyEvent(eventId = 99312)
interface EnableCallSoundNotifications

@LegacyEvent(eventId = 99314)
interface DisableCallSoundNotifications

@LegacyEvent(eventId = 99315)
interface EndCallWhenEmptyCallTimeout
