package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class ScreenViewVisitor(private val idGenerator: IdGenerator) :
    KSDefaultVisitor<EventData, EventResponse>() {

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: EventData,
    ): EventResponse {
        val shortName = classDeclaration.qualifiedName?.getShortName()
            ?: throw VisitorException("Qualified name is null")
        val newMap = idGenerator.invoke(shortName, data.idMap)

        return EventResponse(
            newMap,
            TypeSpec.objectBuilder(getClassName(shortName))
                .addSuperinterface(ScreenViewEventIdentifier::class)
                .addProperty(
                    createEventNameProperty(shortName)
                )
                .addProperty(
                    createUniqueIdentifierProperty(
                        newMap[shortName]
                            ?: throw VisitorException("No id added to map for $shortName")
                    )
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


    @Throws(NotImplementedError::class)
    override fun defaultHandler(node: KSNode, data: EventData) =
        throw NotImplementedError("The called function has not been implemented on ${ScreenViewVisitor::class.simpleName}")
}