package mega.privacy.mobile.analytics.core.event

import mega.privacy.mobile.analytics.core.event.identifier.AppIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.core.event.type.AnalyticsEvent
import mega.privacy.mobile.analytics.core.event.type.ButtonPressedEvent
import mega.privacy.mobile.analytics.core.event.type.DialogDisplayedEvent
import mega.privacy.mobile.analytics.core.event.type.GeneralEvent
import mega.privacy.mobile.analytics.core.event.type.ItemSelectedEvent
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
                eventIdentifier,
                viewIdProvider(),
                appIdentifier
            )

            is ButtonPressedEventIdentifier -> ButtonPressedEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is DialogDisplayedEventIdentifier -> DialogDisplayedEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is GeneralEventIdentifier -> GeneralEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is ItemSelectedEventIdentifier -> ItemSelectedEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is MenuItemEventIdentifier -> MenuItemEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is NavigationEventIdentifier -> NavigationEvent(
                eventIdentifier,
                currentViewId,
                appIdentifier
            )

            is NotificationEventIdentifier -> NotificationEvent(
                eventIdentifier,
                appIdentifier
            )

            is TabSelectedEventIdentifier -> TabSelectedEvent(
                eventIdentifier,
                currentViewId ?: viewIdProvider(),
                appIdentifier
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