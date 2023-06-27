package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.factory.AnnotationVisitorFactory
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import kotlin.reflect.KClass

/**
 * Event generator
 *
 * @property codeGenerator
 * @property idProvider
 */
class EventCodeGenerator(
    private val codeGenerator: CodeGenerator,
    private val idProvider: IdProvider,
    private val visitorFactory: AnnotationVisitorFactory,
    private val annotationClass: KClass<*>,
) {


    /**
     * Generate
     *
     * @param resolver
     * @return Unresolved annotations
     */
    fun generate(resolver: Resolver, packageName: String, fileName: String): List<KSAnnotated> {
        val events = resolver.findAnnotations(annotationClass)
        if (!events.iterator().hasNext()) return emptyList()

        val fileSpec = FileSpec.builder(
            packageName = packageName,
            fileName = fileName
        )

        var latestMap =
            idProvider.loadIdentifiers(annotationClass)
        events.forEach {
            val result = visitorFactory(annotationClass).visitClassDeclaration(
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
        return (events).filterNot { it.validate() }.toList()
    }

    /**
     * Find annotations
     *
     * @param kClass
     */
    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSClassDeclaration>()
}