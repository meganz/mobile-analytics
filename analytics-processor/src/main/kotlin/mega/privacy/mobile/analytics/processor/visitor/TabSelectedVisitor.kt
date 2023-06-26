package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator

/**
 * Tab selected visitor
 *
 * @constructor
 *
 * @param idGenerator
 */
class TabSelectedVisitor(idGenerator: IdGenerator) :
    AnalyticsEventVisitor(idGenerator, TabSelectedEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == TabSelectedEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${TabSelectedEvent::class.java.simpleName} not found.")

        return listOf(
            createTabNameProperty(annotation),
            createScreenNameProperty(annotation)
        )
    }

    private fun createTabNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = TabSelectedEventIdentifier::tabName.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(TabSelectedEvent::tabName.name)
            )
            .build()
    }

    private fun createScreenNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = TabSelectedEventIdentifier::screenName.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(TabSelectedEvent::screenName.name)
            )
            .build()
    }

}