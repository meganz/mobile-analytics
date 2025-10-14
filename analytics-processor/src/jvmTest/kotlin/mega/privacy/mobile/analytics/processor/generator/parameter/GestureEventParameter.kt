package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class GestureEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "GestureEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """

import mega.privacy.mobile.analytics.annotations.GestureEvent

@GestureEvent(gesture = "gesture1", screen = "screen1")
interface TestGesture

@GestureEvent(gesture = "gesture2")
interface TestGesture2
    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.GestureEventIdentifier
        
        public object TestGestureEvent : GestureEventIdentifier {
          override val eventName: String = "TestGesture"
        
          override val uniqueIdentifier: Int = 0
        
          override val gestureName: String = "gesture1"
        
          override val screenName: String? = "screen1"
        }
        
        public object TestGesture2Event : GestureEventIdentifier {
          override val eventName: String = "TestGesture2"
        
          override val uniqueIdentifier: Int = 1
        
          override val gestureName: String = "gesture2"
        
          override val screenName: String? = null
        }

    """.trimIndent()
}