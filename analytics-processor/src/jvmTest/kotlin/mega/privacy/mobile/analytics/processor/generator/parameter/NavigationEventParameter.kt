package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class NavigationEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "NavigationEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.annotations.NavigationEvent.NavigationElementType

@NavigationEvent(destination = "destination1", navigationElementType = NavigationEvent.NavigationElementType.Drawer)
interface TestNavigationItem
"""
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.NavigationEventIdentifier
        
        public object TestNavigationItemEvent : NavigationEventIdentifier {
          override val eventName: String = "TestNavigationItem"
        
          override val uniqueIdentifier: Int = 0
        
          override val destination: String = "destination1"
        
          override val navigationElementType: String = "Drawer"
        }

    """.trimIndent()
}