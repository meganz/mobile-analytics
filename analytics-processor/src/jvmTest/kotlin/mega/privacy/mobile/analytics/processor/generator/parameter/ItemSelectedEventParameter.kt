package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class ItemSelectedEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "ItemSelectedEvents.kt"

    override val sourceFile: SourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.annotations.StaticValue

@ItemSelectedEvent
interface Test1

@ItemSelectedEvent
interface Test2

@ItemSelectedEvent
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
    import mega.privacy.mobile.analytics.core.event.identifier.ItemSelectedEventIdentifier
    
    public object Test1Event : ItemSelectedEventIdentifier() {
      override val eventName: String = "Test1"
    
      override val uniqueIdentifier: Int = 0
    
      override val info: Map<String, Any?> = emptyMap()
    
    }
    
    public object Test2Event : ItemSelectedEventIdentifier() {
      override val eventName: String = "Test2"
    
      override val uniqueIdentifier: Int = 1
    
      override val info: Map<String, Any?> = emptyMap()
    
    }
    
    public class Test3Event(
      foo: Int,
    ) : ItemSelectedEventIdentifier() {
      override val eventName: String = "Test3"
    
      override val uniqueIdentifier: Int = 2
    
      override val info: Map<String, Any?> = mapOf(
      "foo" to foo,
      "bar" to "my value",
      )
    }

    """.trimIndent()
}