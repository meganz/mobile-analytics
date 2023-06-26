package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.visitor.KSDefaultVisitor
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse
import kotlin.reflect.KClass

/**
 * Annotation visitor factory
 *
 * @property idGenerator
 */
class AnnotationVisitorFactory(private val idGenerator: IdGenerator) {
    /**
     * Invoke
     *
     * @param annotationType
     * @return Visitor for the annotation type
     */
    operator fun invoke(annotationType: KClass<*>): KSDefaultVisitor<EventData, EventResponse> {
        return when (annotationType) {
            ScreenViewEvent::class -> ScreenViewVisitor(idGenerator = idGenerator)

            TabSelectedEvent::class -> TabSelectedVisitor(idGenerator = idGenerator)

            GeneralEvent::class -> GeneralEventVisitor(
                constructorParameterMapper = ConstructorParameterMapper(),
                idGenerator = idGenerator
            )

            ButtonPressEvent::class -> ButtonPressVisitor(idGenerator = idGenerator)

            else -> throw IllegalArgumentException("No visitor class registered for event type ${annotationType.simpleName}. Please add registration to ${AnnotationVisitorFactory::class.simpleName}")
        }
    }
}