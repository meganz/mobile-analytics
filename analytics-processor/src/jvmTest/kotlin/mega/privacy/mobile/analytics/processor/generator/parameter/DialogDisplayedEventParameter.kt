package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class DialogDisplayedEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "DialogDisplayedEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent

@DialogDisplayedEvent(dialog = "dialog1", screen = "screen1")
interface TestDialog

@DialogDisplayedEvent(dialog = "dialog2")
interface TestDialog2
    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.DialogDisplayedEventIdentifier
        
        public object TestDialogEvent : DialogDisplayedEventIdentifier() {
          override val eventName: String = "TestDialog"
        
          override val uniqueIdentifier: Int = 0
        
          override val dialogName: String = "dialog1"
        
          override val screenName: String? = "screen1"
        }
        
        public object TestDialog2Event : DialogDisplayedEventIdentifier() {
          override val eventName: String = "TestDialog2"
        
          override val uniqueIdentifier: Int = 1
        
          override val dialogName: String = "dialog2"
        
          override val screenName: String? = null
        }

    """.trimIndent()
}