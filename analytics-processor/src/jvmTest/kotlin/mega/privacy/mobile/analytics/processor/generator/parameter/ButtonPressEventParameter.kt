package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class ButtonPressEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "ButtonPressEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent

@ButtonPressEvent(buttonName = "button1", screenName = "screen1")
interface TestButton
    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.ButtonPressedEventIdentifier
        
        public object TestButtonEvent : ButtonPressedEventIdentifier {
          override val eventName: String = "TestButton"
        
          override val uniqueIdentifier: Int = 0
        
          override val buttonName: String = "button1"
        
          override val screenName: String? = null
        
          override val dialogName: String? = null
        }

    """.trimIndent()
}