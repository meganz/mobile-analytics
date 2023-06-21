package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import mega.privacy.mobile.analytics.processor.generator.ScreenViewEventGenerator

/**
 * Analytics event processor
 */
class AnalyticsEventProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>,
) : SymbolProcessor {
    private val idProvider = IdProvider(logger, options["resourcePath"])

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing started...")
        val screenViewEventGenerator = ScreenViewEventGenerator(codeGenerator, idProvider)
        return screenViewEventGenerator.generate(resolver)
    }

}