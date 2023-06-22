package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.TabSelectedEventData
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse

class TabSelectedVisitor(private val idGenerator: IdGenerator) :
    KSDefaultVisitor<TabSelectedEventData, EventResponse>() {

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: TabSelectedEventData,
    ): EventResponse {
        val shortName = classDeclaration.qualifiedName?.getShortName()
            ?: throw VisitorException("Qualified name is null")
        val newMap = idGenerator.invoke(shortName, data.idMap)

        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == TabSelectedEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${TabSelectedEvent::class.java.simpleName} not found.")

        return EventResponse(
            idMap = newMap,
            spec = TypeSpec.objectBuilder(getClassName(shortName))
                .addSuperinterface(TabSelectedEventIdentifier::class)
                .addProperty(
                    createEventNameProperty(shortName)
                )
                .addProperty(
                    createUniqueIdentifierProperty(
                        newMap[shortName]
                            ?: throw VisitorException("No id added to map for $shortName")
                    )
                )
                .addProperty(
                    createTabNameProperty(annotation)
                )
                .addProperty(
                    createScreenNameProperty(annotation)
                )
                .build()
        )
    }

    private fun getClassName(shortName: String) = "${shortName}Event"

    private fun createEventNameProperty(shortName: String) =
        PropertySpec.builder(
            name = EventIdentifier::eventName.name,
            type = String::class
        ).initializer("%S", shortName)
            .addModifiers(KModifier.OVERRIDE)
            .build()

    private fun createUniqueIdentifierProperty(id: Int) =
        PropertySpec.builder(
            name = EventIdentifier::uniqueIdentifier.name,
            type = Int::class
        ).initializer("%L", id)
            .addModifiers(KModifier.OVERRIDE)
            .build()

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

    private fun KSAnnotation.getParameterValue(name: String) = arguments
        .find { it.name?.getShortName() == name }
        ?.value

    @Throws(NotImplementedError::class)
    override fun defaultHandler(node: KSNode, data: TabSelectedEventData) =
        throw NotImplementedError("The called function has not been implemented on ${TabSelectedVisitor::class.simpleName}")
}