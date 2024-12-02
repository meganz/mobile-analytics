package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
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
class MenuItemEventVisitor(idGenerator: IdGenerator<GenerateSimpleIdRequest>) :
    SimpleIdAnalyticsEventVisitor(idGenerator, MenuItemEventIdentifier::class) {

    override fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == MenuItemEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${MenuItemEvent::class.java.simpleName} not found.")

        return listOf(
            createMenuItemNameProperty(annotation),
            createMenuTypeProperty(annotation),
            createScreenNameProperty(annotation),
        )
    }

    private fun createMenuItemNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = MenuItemEventIdentifier::menuItem.name,
            type = String::class
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                annotation.getParameterValue(MenuItemEvent::menuItem.name)
            )
            .build()
    }

    private fun createMenuTypeProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = MenuItemEventIdentifier::menuType.name,
            type = String::class.asTypeName()
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                getEnumValue(annotation)
            )
            .build()
    }

    private fun getEnumValue(annotation: KSAnnotation): String {
        val parameterValue = annotation.getParameterValue(MenuItemEvent::menuType.name).toString()
        val enumValue = parameterValue.split('.').last()
        return MenuItemEvent.MenuType.valueOf(enumValue).name
    }

    private fun createScreenNameProperty(annotation: KSAnnotation): PropertySpec {
        return PropertySpec.builder(
            name = MenuItemEventIdentifier::screenName.name,
            type = String::class.asTypeName().copy(nullable = true)
        ).addModifiers(KModifier.OVERRIDE)
            .initializer(
                "%S",
                (annotation.getParameterValue(MenuItemEvent::screen.name) as? String).takeUnless { it.isNullOrBlank() }
            )
            .build()
    }

}
