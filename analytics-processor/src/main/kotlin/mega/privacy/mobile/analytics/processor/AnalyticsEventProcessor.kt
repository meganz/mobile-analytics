package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.generator.EventCodeGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.factory.AnnotationVisitorFactory

/**
 * Analytics event processor
 */
class AnalyticsEventProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val idProvider = IdProvider(logger, options[resourcePathKey])
    private val idGenerator = SingleRangeIdGenerator(0..999)
    private val visitorFactory = AnnotationVisitorFactory(idGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing started...")
        val packageName = "mega.privacy.mobile.analytics.event"
        return listOf(
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = ScreenViewEvent::class
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "ScreenViewEvents"
            ),
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = TabSelectedEvent::class
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "TabSelectedEvents"
            ),
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = GeneralEvent::class,
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "GeneralEvents"
            ),
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = ButtonPressEvent::class,
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "ButtonPressEvents"
            ),
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = ItemSelectedEvent::class,
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "ItemSelectedEvents"
            ),
            EventCodeGenerator(
                codeGenerator = codeGenerator,
                idProvider = idProvider,
                visitorFactory = visitorFactory,
                annotationClass = MenuItemEvent::class,
            ).generate(
                resolver = resolver,
                packageName = packageName,
                fileName = "MenuItemEvents"
            ),
        ).flatten()
    }

    companion object {
        /**
         * Resource path key
         */
        const val resourcePathKey = "resourcePath"
    }

}
