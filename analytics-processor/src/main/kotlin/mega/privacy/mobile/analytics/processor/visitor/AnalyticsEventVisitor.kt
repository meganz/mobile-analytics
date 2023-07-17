package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse
import kotlin.reflect.KClass

/**
 * Analytics event visitor
 *
 * @property idGenerator
 * @property eventIdentifierClass
 */
abstract class AnalyticsEventVisitor(
    protected val idGenerator: IdGenerator,
    private val eventIdentifierClass: KClass<*>,
) : KSDefaultVisitor<EventData, EventResponse>() {

    /**
     * Get constructor
     *
     * @param classDeclaration
     * @return Constructor Fun Spec
     */
    protected open fun getConstructor(classDeclaration: KSClassDeclaration): FunSpec? = null

    /**
     * Get properties
     *
     * @param classDeclaration
     * @return list of additional PropertySpec objects
     */
    protected open fun getProperties(classDeclaration: KSClassDeclaration): List<PropertySpec> =
        emptyList()

    /**
     * Get type builder spec type
     *
     * @param className
     * @return Builder of the correct type
     */
    protected open fun getTypeBuilderSpecType(
        className: String,
        classDeclaration: KSClassDeclaration,
    ): TypeSpec.Builder =
        TypeSpec.objectBuilder(className)

    /**
     * Visit class declaration
     *
     * @param classDeclaration
     * @param data
     * @return EventResponse
     */
    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: EventData,
    ): EventResponse {
        val shortName = getAnnotatedClassName(classDeclaration)
        val newMap = idGenerator.invoke(shortName, data.idMap)

        return EventResponse(
            newMap,
            createEventClassBuilder(
                classDeclaration = classDeclaration,
                shortName = shortName,
                newMap = newMap
            ).build()
        )
    }

    private fun getAnnotatedClassName(classDeclaration: KSClassDeclaration): String =
        classDeclaration.qualifiedName?.getShortName()
            ?: throw VisitorException("Qualified name is null")

    private fun createEventClassBuilder(
        classDeclaration: KSClassDeclaration,
        shortName: String,
        newMap: Map<String, Int>,
    ) = getTypeBuilderSpecType(
        className = getClassName(shortName),
        classDeclaration = classDeclaration
    ).addSuperinterface(eventIdentifierClass)
        .addProperty(
            createEventNameProperty(shortName)
        )
        .addProperty(
            createUniqueIdentifierProperty(
                newMap[shortName]
                    ?: throw VisitorException("No id added to map for $shortName")
            )
        ).addSpecs(classDeclaration)

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

    /**
     * Default handler
     *
     * @param node
     * @param data
     */
    @Throws(NotImplementedError::class)
    override fun defaultHandler(node: KSNode, data: EventData) =
        throw NotImplementedError("The called function has not been implemented on ${AnalyticsEventVisitor::class.simpleName}")

    private fun TypeSpec.Builder.addSpecs(classDeclaration: KSClassDeclaration): TypeSpec.Builder {
        getConstructor(classDeclaration)?.let { primaryConstructor(it) }
        getProperties(classDeclaration).forEach {
            addProperty(it)
        }

        return this
    }

    /**
     * Get parameter value
     *
     * @param name
     */
    protected fun KSAnnotation.getParameterValue(name: String) = arguments
        .find { it.name?.getShortName() == name }
        ?.value

}

