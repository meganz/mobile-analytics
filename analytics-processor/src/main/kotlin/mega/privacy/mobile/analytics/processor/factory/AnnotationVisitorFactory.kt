package mega.privacy.mobile.analytics.processor.factory

import com.google.devtools.ksp.visitor.KSDefaultVisitor
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.GestureEvent
import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.annotations.NotificationEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.visitor.ButtonPressVisitor
import mega.privacy.mobile.analytics.processor.visitor.DialogDisplayedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.GestureEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ItemSelectedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.LegacyEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.MenuItemEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.NavigationEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.NotificationEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse
import kotlin.reflect.KClass

/**
 * Annotation visitor factory
 */
class AnnotationVisitorFactory(private val idGeneratorFactory: IdGeneratorFactory) {
    /**
     * Invoke
     *
     * @param annotationType
     * @return Visitor for the annotation type
     */
    operator fun invoke(annotationType: KClass<*>): KSDefaultVisitor<EventData, EventResponse> {
        return when (annotationType) {
            ScreenViewEvent::class -> ScreenViewVisitor(
                idGenerator = idGeneratorFactory(
                    ScreenViewVisitor::class
                )
            )

            TabSelectedEvent::class -> TabSelectedVisitor(
                idGenerator = idGeneratorFactory(
                    TabSelectedVisitor::class
                )
            )

            GeneralEvent::class -> GeneralEventVisitor(
                constructorParameterMapper = ConstructorParameterMapper(),
                idGenerator = idGeneratorFactory(GeneralEventVisitor::class)
            )

            ButtonPressEvent::class -> ButtonPressVisitor(
                idGenerator = idGeneratorFactory(
                    ButtonPressVisitor::class
                )
            )

            ItemSelectedEvent::class -> ItemSelectedEventVisitor(
                constructorParameterMapper = ConstructorParameterMapper(),
                idGenerator = idGeneratorFactory(ItemSelectedEventVisitor::class)
            )

            MenuItemEvent::class -> MenuItemEventVisitor(
                idGenerator = idGeneratorFactory(
                    MenuItemEventVisitor::class
                )
            )

            NavigationEvent::class -> NavigationEventVisitor(
                idGenerator = idGeneratorFactory(
                    NavigationEventVisitor::class
                )
            )

            NotificationEvent::class -> NotificationEventVisitor(
                idGenerator = idGeneratorFactory(
                    NotificationEventVisitor::class
                )
            )

            DialogDisplayedEvent::class -> DialogDisplayedEventVisitor(
                idGenerator = idGeneratorFactory(
                    DialogDisplayedEventVisitor::class
                )
            )

            LegacyEvent::class -> LegacyEventVisitor(
                idGenerator = idGeneratorFactory(
                    LegacyEventVisitor::class
                )
            )

            GestureEvent::class -> GestureEventVisitor(
                idGenerator = idGeneratorFactory(
                    GestureEventVisitor::class
                )
            )

            else -> throw IllegalArgumentException("No visitor class registered for event type ${annotationType.simpleName}. Please add registration to ${AnnotationVisitorFactory::class.simpleName}")
        }
    }
}