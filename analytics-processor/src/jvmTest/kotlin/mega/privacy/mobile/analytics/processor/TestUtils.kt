package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSName
import mega.privacy.mobile.analytics.processor.AnalyticsEventProcessor
import org.mockito.kotlin.mock

class TestProcessorProvider(private val options: Map<String, String>) : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return AnalyticsEventProcessor(
            environment.codeGenerator,
            environment.logger,
            options
        )
    }

}

fun String.mockShortName() =
    mock<KSName> { on { getShortName() }.thenReturn(this@mockShortName) }