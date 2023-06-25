package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.core.event.identifier.EventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.GeneralEventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class GeneralEventVisitor(
    private val idGenerator: IdGenerator,
    private val constructorParameterMapper: ConstructorParameterMapper,
) :
    KSDefaultVisitor<GeneralEventData, EventResponse>() {

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: GeneralEventData,
    ): EventResponse {
        val shortName = classDeclaration.qualifiedName?.getShortName()
            ?: throw VisitorException("Qualified name is null")
        val newMap = idGenerator.invoke(shortName, data.idMap)


        val constructorValues: List<KSValueParameter>? =
            classDeclaration.primaryConstructor?.parameters


//        Create constructor
        val constructorParameters = constructorValues?.let { parameters ->
            parameters.mapNotNull { ksValueParameter ->
                constructorParameterMapper(ksValueParameter)
            }
        }
        val typeSpecBuilder = if (constructorParameters.isNullOrEmpty()) {
            TypeSpec.objectBuilder(getClassName(shortName))
        } else {
            TypeSpec.classBuilder(getClassName(shortName))
                .primaryConstructor(
                    FunSpec.constructorBuilder().apply {
                        constructorParameters.forEach {
                            addParameter(it)
                        }
                    }.build()
                )
        }

        //Add general event properties
        typeSpecBuilder.addSuperinterface(GeneralEventIdentifier::class)
            .addProperty(
                createEventNameProperty(shortName)
            )
            .addProperty(
                createUniqueIdentifierProperty(
                    newMap[shortName]
                        ?: throw VisitorException("No id added to map for $shortName")
                )
            )

        typeSpecBuilder.addProperty(
            createInfoProperty(constructorValues)
        )

//        //Add field values
//        classDeclaration.getAllProperties().forEach {
//            if (it.)
//        }

        return EventResponse(
            newMap,
            typeSpecBuilder.build()
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

    private fun createInfoProperty(constructorValues: List<KSValueParameter>?) =
        createIngoPropertyBuilder()
            .addModifiers(KModifier.OVERRIDE)
            .initializer(createInfoPropertyInitialiser(constructorValues))
            .build()

    private fun createInfoPropertyInitialiser(constructorValues: List<KSValueParameter>?): CodeBlock {
        val initialiser = CodeBlock.builder()
        initialiser.addStatement("mapOf(")
        constructorValues?.mapNotNull { it.name?.getShortName() }
            ?.forEach {
                initialiser.addStatement(
                    "\"${it}\" to $it,"
                )
            }
        initialiser.add(")")
        return initialiser.build()
    }

    private fun createIngoPropertyBuilder() = PropertySpec.builder(
        name = GeneralEventIdentifier::info.name,
        type = Map::class.asClassName()
            .parameterizedBy(
                String::class.asTypeName(),
                Any::class.asTypeName().copy(nullable = true)
            )
    )


    @Throws(NotImplementedError::class)
    override fun defaultHandler(node: KSNode, data: GeneralEventData) =
        throw NotImplementedError("The called function has not been implemented on ${GeneralEventVisitor::class.simpleName}")
}