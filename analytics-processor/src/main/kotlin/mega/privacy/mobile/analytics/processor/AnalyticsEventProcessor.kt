package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import mega.privacy.mobile.analytics.processor.generator.ScreenViewEventGenerator
import mega.privacy.mobile.analytics.processor.identifier.IdProvider
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator

/**
 * Analytics event processor
 */
class AnalyticsEventProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    options: Map<String, String>,
) : SymbolProcessor {
    private val idProvider = IdProvider(logger, options[resourcePathKey])

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing started...")
        val screenViewEventGenerator = ScreenViewEventGenerator(
            codeGenerator = codeGenerator,
            idProvider = idProvider,
            idGenerator = SingleRangeIdGenerator(0..999)
        )
        return screenViewEventGenerator.generate(
            resolver = resolver,
            packageName = "mega.privacy.mobile.analytics.event",
            fileName = "ScreenViewEvents"
        )
    }

    companion object{
        /**
         * Resource path key
         */
        const val resourcePathKey = "resourcePath"
    }

}