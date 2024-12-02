package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest

/**
 * Menu item event visitor
 *
 * @constructor
 *
 * @param idGenerator
 */
class NavigationEventVisitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) :
    SimpleIdAnalyticsEventVisitor(idGenerator, NavigationEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == NavigationEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${NavigationEvent::class.java.simpleName} not found.")

        return listOf(
            createDestinationProperty(annotation),
            createNavigationElementTypeProperty(annotation),
        )
    }

    private fun createDestinationProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = NavigationEventIdentifier::destination.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(NavigationEvent::destination.name)
            )
            .build()
    }

    private fun createNavigationElementTypeProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = NavigationEventIdentifier::navigationElementType.name,
            type = String::class.asTypeName()
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                getEnumValue(annotation)
            )
            .build()
    }

    private fun getEnumValue(annotation: KSAnnotation): String {
        val parameterValue =
            annotation.getParameterValue(NavigationEvent::navigationElementType.name).toString()
        val enumValue = parameterValue.split('.').last()
        return NavigationEvent.NavigationElementType.valueOf(enumValue).name
    }

}
