package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.processor.findAnnotations
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import kotlin.reflect.KClass

/**
 * Screen view event generator
 *
 * @property codeGenerator
 * @property idProvider
 * @property idGenerator
 */
class ScreenViewEventGenerator(
    private val codeGenerator: CodeGenerator,
    private val idProvider: IdProvider,
    private val idGenerator: IdGenerator,
    private val annotationClass: KClass<*> = ScreenViewEvent::class,
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
            val result = ScreenViewVisitor(idGenerator)
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
        return (events).filterNot { it.validate() }.toList()
    }
}