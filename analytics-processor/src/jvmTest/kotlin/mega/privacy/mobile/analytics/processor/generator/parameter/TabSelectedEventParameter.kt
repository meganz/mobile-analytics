package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class TabSelectedEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "TabSelectedEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent

@TabSelectedEvent(tabName = "tab1", screenName = "screen1")
interface TestTab1

@TabSelectedEvent(tabName = "tab2", screenName = "screen2")
interface TestTab2

@TabSelectedEvent(tabName = "tab3", screenName = "screen3")
interface TestTab3

    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.TabSelectedEventIdentifier
        
        public object TestTab1Event : TabSelectedEventIdentifier {
          override val eventName: String = "TestTab1"
        
          override val uniqueIdentifier: Int = 0
        
          override val tabName: String = "tab1"
        
          override val screenName: String = "screen1"
        }
        
        public object TestTab2Event : TabSelectedEventIdentifier {
          override val eventName: String = "TestTab2"
        
          override val uniqueIdentifier: Int = 1
        
          override val tabName: String = "tab2"
        
          override val screenName: String = "screen2"
        }
        
        public object TestTab3Event : TabSelectedEventIdentifier {
          override val eventName: String = "TestTab3"
        
          override val uniqueIdentifier: Int = 2
        
          override val tabName: String = "tab3"
        
          override val screenName: String = "screen3"
        }

    """.trimIndent()
}