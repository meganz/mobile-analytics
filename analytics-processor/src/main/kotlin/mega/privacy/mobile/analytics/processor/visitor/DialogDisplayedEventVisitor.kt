package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent
import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator

/**
 * Dialog displayed event visitor
 *
 * @constructor
 *
 * @param idGenerator
 */
class DialogDisplayedEventVisitor(idGenerator: IdGenerator) :
    AnalyticsEventVisitor(idGenerator, DialogDisplayedEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == DialogDisplayedEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${DialogDisplayedEvent::class.java.simpleName} not found.")

        return listOf(
            createDialogNameProperty(annotation),
            createScreenNameProperty(annotation),
        )
    }

    private fun createDialogNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = DialogDisplayedEventIdentifier::dialogName.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(DialogDisplayedEvent::dialog.name)
            )
            .build()
    }

    private fun createScreenNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = DialogDisplayedEventIdentifier::screenName.name,
            type = String::class.asTypeName().copy(nullable = true)
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                (annotation.getParameterValue(DialogDisplayedEvent::screen.name) as? String).takeUnless { it.isNullOrBlank() }
            )
            .build()
    }

}