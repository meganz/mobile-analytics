package mega.privacy.mobile.analytics.processor

import com.google.devtools.ksp.processing.KSPLogger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.io.File
import kotlin.reflect.KClass

/**
 * Id provider
 *
 * @property logger
 * @property resourcePath
 */
class IdProvider(
    private val logger: KSPLogger,
    private val resourcePath: String?,
) {

    init {
        require(resourcePath != null) { "No resource path defined. Please check the path in ksp.gradle.kts" }
        logger.info("Resource path is set to $resourcePath")
        val resourceDirectory = File(resourcePath)
        require(resourceDirectory.exists() && resourceDirectory.isDirectory) { "Resource path does not exist or is not a directory. Please check the path in ksp.gradle.kts" }
    }

    /**
     * Load identifiers
     *
     * @param annotationClass
     * @return current identifiers
     */
    fun loadIdentifiers(annotationClass: KClass<*>): Map<String, Int> {
        logger.info("Loading identifiers for ${annotationClass.simpleName} from $resourcePath")
        val screenViewEventsJson: String =
            getIdentifierResourceJson(annotationClass)
        return Json.decodeFromString(screenViewEventsJson)
    }

    private fun getIdentifierResourceJson(annotationClass: KClass<*>) =
        getResourceFile(annotationClass).readText(Charsets.UTF_8)

    private fun getResourceFile(annotationClass: KClass<*>): File {
        val filename = "${annotationClass.simpleName}.json"
        val file = File(resourcePath, filename)
        if (!file.exists()) file.createNewFile()
        return file
    }

    /**
     * Save identifiers
     *
     * @param map
     * @param annotationClass
     */
    fun saveIdentifiers(map: Map<String, Int>, annotationClass: KClass<*>) {
        logger.info("Saving updated identifiers for ${annotationClass.simpleName} to $resourcePath")
        val resourceFile = getResourceFile(annotationClass)
        val jsonString = JsonObject(map.mapValues { JsonPrimitive(it.value) }).toString()
        resourceFile.writeText(jsonString, Charsets.UTF_8)
    }
}