package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class GeneralEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "GeneralEvents.kt"

    override val sourceFile: SourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.StaticValue

@GeneralEvent
interface Test1

@GeneralEvent
interface Test2

@GeneralEvent
class Test3(
    val foo: Int,
    @StaticValue("my value")
    val bar: String,
)

    """
    )

    override val expectedOutput =
        """
    package mega.privacy.mobile.analytics.event
    
    import kotlin.Any
    import kotlin.Int
    import kotlin.String
    import kotlin.collections.Map
    import mega.privacy.mobile.analytics.core.event.identifier.GeneralEventIdentifier
    
    public object Test1Event : GeneralEventIdentifier {
      override val eventName: String = "Test1"
    
      override val uniqueIdentifier: Int = 0
    
      override val info: Map<String, Any?> = emptyMap()
    }
    
    public object Test2Event : GeneralEventIdentifier {
      override val eventName: String = "Test2"
    
      override val uniqueIdentifier: Int = 1
    
      override val info: Map<String, Any?> = emptyMap()
    }
    
    public class Test3Event(
      foo: Int,
    ) : GeneralEventIdentifier {
      override val eventName: String = "Test3"
    
      override val uniqueIdentifier: Int = 2
    
      override val info: Map<String, Any?> = mapOf(
      "foo" to foo,
      "bar" to "my value",
      )
    }
    """.trimIndent()
}