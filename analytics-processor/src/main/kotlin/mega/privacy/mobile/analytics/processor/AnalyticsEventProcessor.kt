package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.generator.EventCodeGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.visitor.AnnotationVisitorFactory

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
        val screenViewEventGenerator = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = visitorFactory,
            annotationClass = ScreenViewEvent::class
        )

        val tabSelectedEventGenerator = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = visitorFactory,
            annotationClass = TabSelectedEvent::class
        )

        val generalEventGenerator = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = visitorFactory,
            annotationClass = GeneralEvent::class,
        )

        val buttonPressEventGenerator = EventCodeGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            visitorFactory = visitorFactory,
            annotationClass = ButtonPressEvent::class,
        )

        val screenViewList: List<KSAnnotated> = screenViewEventGenerator.generate(
            resolver = resolver,
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "ScreenViewEvents"
        )

        val tabSelectedList: List<KSAnnotated> = tabSelectedEventGenerator.generate(
            resolver = resolver,
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "TabSelectedEvents"
        )

        val generalEventsList: List<KSAnnotated> = generalEventGenerator.generate(
            resolver = resolver,
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "GeneralEvents"
        )

        val buttonPressEventsList: List<KSAnnotated> = buttonPressEventGenerator.generate(
            resolver = resolver,
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "ButtonPressEvents"
        )

        return screenViewList + tabSelectedList + generalEventsList + buttonPressEventsList
    }

    companion object {
        /**
         * Resource path key
         */
        const val resourcePathKey = "resourcePath"
    }

}
