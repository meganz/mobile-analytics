package mega.privacy.mobile.analytics.processor.manifest

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.ksp.toTypeName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.annotations.StaticValue
import java.io.File
import kotlin.reflect.KClass

/**
 * Collects the public API of every generated event and writes it as a JSON manifest.
 *
 * The manifest is a build output. The build checks compare the manifest of a baseline commit with
 * the manifest of HEAD, so that a change to an existing event's annotation, parameters or payload
 * is found. The output order is fixed so that two builds of the same sources give the same file.
 *
 * @property manifestPath path of the manifest file to write
 */
class EventManifestWriter(private val manifestPath: String) {

    private val events = mutableMapOf<Pair<String, String>, ManifestEvent>()

    /**
     * Records the event declared by [classDeclaration]
     *
     * @param classDeclaration the annotated event declaration
     * @param annotationClass the event annotation type
     * @param id the index assigned to the event in the identifier JSON file
     */
    fun record(classDeclaration: KSClassDeclaration, annotationClass: KClass<*>, id: Int) {
        val type = annotationClass.simpleName.orEmpty()
        val name = classDeclaration.simpleName.asString()
        val annotation = classDeclaration.annotations.first { it.shortName.asString() == type }
        val (staticParameters, parameters) = classDeclaration.primaryConstructor?.parameters
            .orEmpty()
            .partition { it.staticValue() != null }

        events[type to name] = ManifestEvent(
            name = name,
            type = type,
            id = id,
            legacyId = id.takeIf { annotationClass == LegacyEvent::class },
            annotationArguments = annotation.argumentMap(),
            parameters = parameters.map { it.toManifestParameter() },
            staticValues = staticParameters
                .associate { it.name?.asString().orEmpty() to it.staticValue().orEmpty() }
                .toSortedMap(),
        )
    }

    /**
     * Writes the recorded events to [manifestPath]
     */
    fun write() {
        val manifest = EventManifest(
            events = events.values.sortedWith(compareBy({ it.type }, { it.name }))
        )
        File(manifestPath).apply {
            parentFile?.mkdirs()
            writeText(json.encodeToString(EventManifest.serializer(), manifest) + "\n")
        }
    }

    // Default values are visible when the annotations come from a JVM classpath, but not from the
    // KMP metadata that the shared module uses. The generated code treats a blank value as absent,
    // so blank values are left out to give the same manifest in both cases.
    private fun KSAnnotation.argumentMap(): Map<String, String> = arguments
        .mapNotNull { argument ->
            argument.value.render()?.takeUnless { it.isBlank() }
                ?.let { argument.name?.asString().orEmpty() to it }
        }
        .toMap()
        .toSortedMap()

    private fun Any?.render(): String? = when (this) {
        null -> null
        is KSType -> declaration.simpleName.asString()
        is KSClassDeclaration -> simpleName.asString()
        else -> toString()
    }

    private fun KSValueParameter.staticValue(): String? = annotations
        .find { it.shortName.asString() == StaticValue::class.simpleName }
        ?.arguments
        ?.firstOrNull()
        ?.value
        ?.toString()

    private fun KSValueParameter.toManifestParameter(): ManifestParameter {
        val declaration = type.resolve().declaration as? KSClassDeclaration
        return ManifestParameter(
            name = name?.asString().orEmpty(),
            type = type.toTypeName().toString(),
            enumValues = declaration
                ?.takeIf { it.classKind == ClassKind.ENUM_CLASS }
                ?.declarations
                ?.filterIsInstance<KSClassDeclaration>()
                ?.filter { it.classKind == ClassKind.ENUM_ENTRY }
                ?.map { it.simpleName.asString() }
                ?.toList(),
        )
    }

    private companion object {
        val json = Json { prettyPrint = true }
    }
}

/**
 * Event manifest
 *
 * @property events every generated event, ordered by type and name
 */
@Serializable
data class EventManifest(val events: List<ManifestEvent>)

/**
 * Public API of one generated event
 *
 * @property name event name, which is also the key in the identifier JSON file
 * @property type simple name of the event annotation
 * @property id index assigned in the identifier JSON file
 * @property legacyId the fixed ID of a legacy event, or null for other types
 * @property annotationArguments annotation arguments that are not blank
 * @property parameters constructor parameters that the caller passes at runtime, in declaration order
 * @property staticValues payload entries with a compile-time value from [StaticValue]
 */
@Serializable
data class ManifestEvent(
    val name: String,
    val type: String,
    val id: Int,
    val legacyId: Int?,
    val annotationArguments: Map<String, String>,
    val parameters: List<ManifestParameter>,
    val staticValues: Map<String, String>,
)

/**
 * Constructor parameter of an event
 *
 * @property name parameter name, which is also the payload key
 * @property type fully qualified type, including nullability
 * @property enumValues entries of the type when it is an enum class, because they are payload values
 */
@Serializable
data class ManifestParameter(
    val name: String,
    val type: String,
    val enumValues: List<String>?,
)
