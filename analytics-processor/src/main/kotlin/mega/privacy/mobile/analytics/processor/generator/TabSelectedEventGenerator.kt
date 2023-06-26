package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.findAnnotations
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import kotlin.reflect.KClass

/**
 * Tab selected event generator
 *
 * @property codeGenerator
 * @property idProvider
 * @property idGenerator
 */
class TabSelectedEventGenerator(
    private val codeGenerator: CodeGenerator,
    private val idProvider: IdProvider,
    private val idGenerator: IdGenerator,
    private val annotationClass: KClass<*> = TabSelectedEvent::class,
) {


    /**
     * Generate
     *
     * @param resolver
     * @param packageName
     * @param fileName
     * @return unhandled classes
     */
    fun generate(resolver: Resolver, packageName: String, fileName: String): List<KSAnnotated> {
        val tabEvents = resolver.findAnnotations(annotationClass)
        if (!tabEvents.iterator().hasNext()) return emptyList()

        val fileSpec = FileSpec.builder(
            packageName = packageName,
            fileName = fileName
        )

        var latestMap =
            idProvider.loadIdentifiers(annotationClass)
        tabEvents.forEach {
            val result = TabSelectedVisitor(idGenerator)
                .visitClassDeclaration(
                    it, EventData(
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
        return (tabEvents).filterNot { it.validate() }.toList()
    }
}