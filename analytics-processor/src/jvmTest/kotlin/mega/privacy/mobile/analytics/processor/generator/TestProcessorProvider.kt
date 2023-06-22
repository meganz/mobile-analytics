package mega.privacy.mobile.analytics.processor.generator

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor

class TestProcessorProvider(private val options: Map<String, String>) : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return AnalyticsEventProcessor(
            environment.codeGenerator,
            environment.logger,
            options
        )
    }

}