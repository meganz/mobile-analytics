package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.annotations.NotificationEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.factory.AnnotationVisitorFactory
import mega.privacy.mobile.analytics.processor.factory.IdGeneratorFactory
import mega.privacy.mobile.analytics.processor.generator.EventCodeGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import kotlin.reflect.KClass

/**
 * Analytics event processor
 */
class AnalyticsEventProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val idProvider = IdProvider(logger, options[RESOURCE_PATH_KEY])
    private val idGeneratorFactory = IdGeneratorFactory()
    private val visitorFactory = AnnotationVisitorFactory(idGeneratorFactory)
    private val analyticsPackageName = "mega.privacy.mobile.analytics.event"

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing started...")
        return listOf(
            getGenerator(
                annotationClass = ScreenViewEvent::class
            ).generate(
                resolver = resolver,
                fileName = "ScreenViewEvents"
            ),
            getGenerator(
                annotationClass = TabSelectedEvent::class
            ).generate(
                resolver = resolver,
                fileName = "TabSelectedEvents"
            ),
            getGenerator(
                annotationClass = GeneralEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "GeneralEvents"
            ),
            getGenerator(
                annotationClass = ButtonPressEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "ButtonPressEvents"
            ),
            getGenerator(
                annotationClass = ItemSelectedEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "ItemSelectedEvents"
            ),
            getGenerator(
                annotationClass = MenuItemEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "MenuItemEvents"
            ),
            getGenerator(
                annotationClass = NavigationEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "NavigationEvents"
            ),
            getGenerator(
                annotationClass = NotificationEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "NotificationEvents"
            ),
            getGenerator(
                annotationClass = DialogDisplayedEvent::class,
            ).generate(
                resolver = resolver,
                fileName = "DialogDisplayedEvents"
            ),
        ).flatten()
    }

    private fun getGenerator(annotationClass: KClass<*>) = EventCodeGenerator(
        codeGenerator = codeGenerator,
        idProvider = idProvider,
        visitorFactory = visitorFactory,
        annotationClass = annotationClass,
    )

    private fun EventCodeGenerator.generate(resolver: Resolver, fileName: String) =
        generate(resolver = resolver, packageName = analyticsPackageName, fileName = fileName)

    companion object {
        /**
         * Resource path key
         */
        const val RESOURCE_PATH_KEY = "resourcePath"
    }

}
