package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import kotlinx.serialization.json.Json
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import kotlin.reflect.KClass

/**
 * Analytics event processor
 */
class AnalyticsEventProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>,
) : SymbolProcessor {
    private val screenMap: MutableMap<String, Int>

    init {
        val screenViewEventsJson: String = getIdentifierResourceJson("ScreenIdentifiers.json")
            ?: throw IllegalStateException("ScreenIdentifiers.json could not be loaded")
        screenMap = Json.decodeFromString(screenViewEventsJson)
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val screens = resolver.findAnnotations(ScreenViewEvent::class)
        if (!screens.iterator().hasNext()) return emptyList()

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies.ALL_FILES,
            packageName = "com.example",
            fileName = "ScreenTest",
            extensionName = "txt"
        )
        file.write("\n Annotated classes \n".toByteArray())
        screens.forEach {
            file.write(
                """
                ${it.simpleName}
                ClassKind: ${it.classKind}
                Package name: ${it.packageName.getShortName()}
                Qualifier: ${it.qualifiedName?.getQualifier()}
                Short name: ${it.qualifiedName?.getShortName()}
            """.trimIndent().toByteArray()
            )
            file.write("\n".toByteArray())
        }
        file.close()

        return (screens).filterNot { it.validate() }.toList()
    }

    /**
     * Get identifier resource json string
     *
     * @param fileName
     */
    fun getIdentifierResourceJson(fileName: String): String? {
        val resource = this::class.java.getResource("/identifiers/$fileName")
        return resource?.readText(Charsets.UTF_8)
    }

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    )
        .filterIsInstance<KSClassDeclaration>()
//        .filter {
//            it.parameters.isEmpty()
//        }
}