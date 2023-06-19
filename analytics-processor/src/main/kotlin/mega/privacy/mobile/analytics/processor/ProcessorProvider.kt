package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * Processor provider
 */
class ProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment) = AnalyticsEventProcessor(
        codeGenerator = environment.codeGenerator,
        logger = environment.logger,
        options = environment.options
    )
}