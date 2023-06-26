package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator

class ButtonPressVisitor(idGenerator: IdGenerator) :
    AnalyticsEventVisitor(idGenerator, ButtonPressedEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == ButtonPressEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${ButtonPressEvent::class.java.simpleName} not found.")

        return listOf(
            createButtonNameProperty(annotation),
            createScreenNameProperty(annotation),
            createDialogNameProperty(annotation),
        )
    }

    private fun createButtonNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = ButtonPressedEventIdentifier::buttonName.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(ButtonPressEvent::buttonName.name)
            )
            .build()
    }

    private fun createScreenNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = ButtonPressedEventIdentifier::screenName.name,
            type = String::class.asTypeName().copy(nullable = true)
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                (annotation.getParameterValue(ButtonPressEvent::screen.name) as? String).takeUnless { it.isNullOrBlank() }
            )
            .build()
    }

    private fun createDialogNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = ButtonPressedEventIdentifier::dialogName.name,
            type = String::class.asTypeName().copy(nullable = true)
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                (annotation.getParameterValue(ButtonPressEvent::dialog.name) as? String).takeUnless { it.isNullOrBlank() }
            )
            .build()
    }

}