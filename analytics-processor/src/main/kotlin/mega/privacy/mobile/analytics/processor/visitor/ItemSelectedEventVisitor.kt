package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import mega.privacy.mobile.analytics.annotations.StaticValue
import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse

/**
 * Screen view visitor
 *
 * @property idGenerator
 */
class ItemSelectedEventVisitor(
    private val constructorParameterMapper: ConstructorParameterMapper,
    idGenerator: IdGenerator,
) : AnalyticsEventVisitor(idGenerator, ItemSelectedEventIdentifier::class) {

    private var constructorValues: List<KSValueParameter>? = null

    override fun getTypeBuilderSpecType(
        className: String,
        classDeclaration: KSClassDeclaration,
    ): TypeSpec.Builder {
        val constructorParameters = constructorValues?.filterStaticAnnotatedParameters()

        return if (constructorParameters.isNullOrEmpty()) {
            TypeSpec.objectBuilder(className)
        } else {
            TypeSpec.classBuilder(className)
        }
    }

    override fun getConstructor(classDeclaration: KSClassDeclaration): FunSpec? = constructorValues
        ?.filterStaticAnnotatedParameters()
        ?.takeUnless { it.isEmpty() }
        ?.let { values ->
            return FunSpec.constructorBuilder().apply {
                values.mapNotNull { ksValueParameter ->
                    constructorParameterMapper(ksValueParameter)
                }.forEach {
                    addParameter(it)
                }
            }.build()
        }

    override fun getProperties(classDeclaration: KSClassDeclaration) = listOf(
        createInfoProperty(
            constructorValues = constructorValues,
        )
    )

    override fun visitClassDeclaration(
        classDeclaration: KSClassDeclaration,
        data: EventData,
    ): EventResponse {
        constructorValues =
            classDeclaration.primaryConstructor?.parameters
        return super.visitClassDeclaration(classDeclaration, data)
    }

    private fun List<KSValueParameter>.filterStaticAnnotatedParameters(): List<KSValueParameter> =
        filterNot { parameter -> parameter.isStaticValueParameter() }

    private fun KSValueParameter.isStaticValueParameter() =
        annotations.any { it.shortName.getShortName() == StaticValue::class.java.simpleName }

    private fun createInfoProperty(
        constructorValues: List<KSValueParameter>?,
    ) = createInfoPropertyBuilder()
        .addModifiers(KModifier.OVERRIDE)
        .initializer(
            createInfoPropertyInitialiser(
                constructorValues = constructorValues,
            )
        ).build()

    private fun createInfoPropertyInitialiser(
        constructorValues: List<KSValueParameter>?,
    ): CodeBlock {
        val initialiser = CodeBlock.builder()
        if (constructorValues.isNullOrEmpty()) {
            initialiser.addStatement("emptyMap()")
        } else {
            initialiser.addStatement("mapOf(")
            constructorValues.forEach {
                val name = it.name?.getShortName()
                val value = getValue(it) ?: name
                initialiser.addStatement(
                    "\"${name}\" to $value,"
                )
            }
            initialiser.add(")")
        }
        return initialiser.build()
    }

    private fun getValue(
        parameter: KSValueParameter,
    ) = parameter.annotations.find {
        it.shortName.getShortName() == StaticValue::class.java.simpleName
    }?.arguments
        ?.first()
        ?.value
        ?.let {
            "\"$it\""
        }


    private fun createInfoPropertyBuilder() = PropertySpec.builder(
        name = GeneralEventIdentifier::info.name,
        type = Map::class.asClassName()
            .parameterizedBy(
                String::class.asTypeName(),
                Any::class.asTypeName().copy(nullable = true)
            )
    )
}