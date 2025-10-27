package mega.privacy.mobile.analytics.core.event

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.type.AnalyticsEvent
import mega.privacy.mobile.analytics.core.event.type.ButtonPressedEvent
import mega.privacy.mobile.analytics.core.event.type.DialogDisplayedEvent
import mega.privacy.mobile.analytics.core.event.type.GeneralEvent
import mega.privacy.mobile.analytics.core.event.type.GestureEvent
import mega.privacy.mobile.analytics.core.event.type.ItemSelectedEvent
import mega.privacy.mobile.analytics.core.event.type.LegacyEvent
import mega.privacy.mobile.analytics.core.event.type.MenuItemEvent
import mega.privacy.mobile.analytics.core.event.type.NavigationEvent
import mega.privacy.mobile.analytics.core.event.type.NotificationEvent
import mega.privacy.mobile.analytics.core.event.type.ScreenViewEvent
import mega.privacy.mobile.analytics.core.event.type.TabSelectedEvent

/**
 * Event generator
 *
 * @property viewIdProvider
 * @property appIdentifier
 */
class EventGenerator(
    private val viewIdProvider: suspend () -> String,
    private val appIdentifier: AppIdentifier
) {
    private var currentViewId: String? = null

    /**
     * Generate event
     *
     * @param eventIdentifier
     * @return event
     */
    suspend fun generateEvent(eventIdentifier: EventIdentifier): AnalyticsEvent {
        return when (eventIdentifier) {
            is ScreenViewEventIdentifier -> trackScreenView(
                eventIdentifier = eventIdentifier,
                viewIdentifier = viewIdProvider(),
                appIdentifier = appIdentifier,
            )

            is ButtonPressedEventIdentifier -> ButtonPressedEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is DialogDisplayedEventIdentifier -> DialogDisplayedEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is GeneralEventIdentifier -> GeneralEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is ItemSelectedEventIdentifier -> ItemSelectedEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is MenuItemEventIdentifier -> MenuItemEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is NavigationEventIdentifier -> NavigationEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is NotificationEventIdentifier -> NotificationEvent(
                eventIdentifier = eventIdentifier,
                appIdentifier = appIdentifier,
            )

            is TabSelectedEventIdentifier -> TabSelectedEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId ?: viewIdProvider(),
                appIdentifier = appIdentifier,
            )

            is LegacyEventIdentifier -> LegacyEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            is GestureEventIdentifier -> GestureEvent(
                eventIdentifier = eventIdentifier,
                viewId = currentViewId,
                appIdentifier = appIdentifier,
            )

            else -> throw NotImplementedError("Not yet implemented")
        }
    }

    private fun trackScreenView(
        eventIdentifier: ScreenViewEventIdentifier,
        viewIdentifier: String,
        appIdentifier: AppIdentifier
    ): ScreenViewEvent {
        currentViewId = viewIdentifier
        return ScreenViewEvent(
            eventIdentifier = eventIdentifier,
            viewId = viewIdentifier,
            appIdentifier = appIdentifier
        )
    }
}