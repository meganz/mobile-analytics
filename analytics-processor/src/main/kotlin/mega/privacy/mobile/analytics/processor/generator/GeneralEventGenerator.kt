package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.processor.findAnnotations
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.GeneralEventData
import mega.privacy.mobile.analytics.processor.visitor.mapper.ConstructorParameterMapper

/**
 * General event generator
 *
 * @property codeGenerator
 * @property idProvider
 * @property idGenerator
 */
class GeneralEventGenerator(
    private val codeGenerator: CodeGenerator,
    private val idProvider: IdProvider,
    private val idGenerator: IdGenerator,
) {

    private val annotationClass = GeneralEvent::class

    /**
     * Generate
     *
     * @param resolver
     * @return Unresolved annotations
     */
    fun generate(resolver: Resolver, packageName: String, fileName: String): List<KSAnnotated> {
        val generalEvents = resolver.findAnnotations(annotationClass)
        if (!generalEvents.iterator().hasNext()) return emptyList()

        val fileSpec = FileSpec.builder(
            packageName = packageName,
            fileName = fileName
        )

        var latestMap =
            idProvider.loadIdentifiers(annotationClass)
        generalEvents.forEach {
            val result = GeneralEventVisitor(
                idGenerator = idGenerator,
                constructorParameterMapper = ConstructorParameterMapper(),
            )
                .visitClassDeclaration(
                    it, GeneralEventData(
                        latestMap
                    )
                )
            latestMap = result.idMap
            fileSpec.addType(result.spec)
        }


        fileSpec.build().writeTo(
            codeGenerator = codeGenerator,
            aggregating = false
        )

        idProvider.saveIdentifiers(latestMap, annotationClass)
        return (generalEvents).filterNot { it.validate() }.toList()
    }
}