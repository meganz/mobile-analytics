package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.GestureEvent
import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest

/**
 * Gesture event visitor
 *
 * @constructor
 *
 * @param idGenerator
 */
class GestureEventVisitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) :
    SimpleIdAnalyticsEventVisitor(idGenerator, GestureEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == GestureEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${GestureEvent::class.java.simpleName} not found.")

        return listOf(
            createGestureNameProperty(annotation),
            createScreenNameProperty(annotation),
        )
    }

    private fun createGestureNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = GestureEventIdentifier::gestureName.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(GestureEvent::gesture.name)
            )
            .build()
    }

    private fun createScreenNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = GestureEventIdentifier::screenName.name,
            type = String::class.asTypeName().copy(nullable = true)
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                (annotation.getParameterValue(GestureEvent::screen.name) as? String).takeUnless { it.isNullOrBlank() }
            )
            .build()
    }

}