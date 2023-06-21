package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.processor.IdGenerator
import mega.privacy.mobile.analytics.processor.IdProvider
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.ScreenViewEventData
import kotlin.reflect.KClass

class ScreenViewEventGenerator(
    private val codeGenerator: CodeGenerator,
    private val idProvider: IdProvider,
) {

    private val idGenerator = IdGenerator(0..999)

    fun generate(resolver: Resolver): List<KSAnnotated> {
        val screens = resolver.findAnnotations(ScreenViewEvent::class)
        if (!screens.iterator().hasNext()) return emptyList()

        val fileSpec = FileSpec.builder(
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "ScreenViewEvents"
        )

        var latestMap =
            idProvider.loadIdentifiers(ScreenViewEvent::class)
        screens.forEach {
            val result = ScreenViewVisitor(idGenerator)
                .visitClassDeclaration(
                    it, ScreenViewEventData(
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

        idProvider.saveIdentifiers(latestMap, ScreenViewEvent::class)
        return (screens).filterNot { it.validate() }.toList()
    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSClassDeclaration>()
}