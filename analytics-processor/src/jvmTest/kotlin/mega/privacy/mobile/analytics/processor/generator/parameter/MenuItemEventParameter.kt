package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class MenuItemEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "MenuItemEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent.MenuType

@MenuItemEvent(menuItem = "item1", screenName = "screen1", menuType = MenuType.Item)
interface TestItem

@MenuItemEvent(menuItem = "item2", menuType = MenuType.Toolbar)
interface TestItem2"""
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.MenuItemEventIdentifier
        
        public object TestItemEvent : MenuItemEventIdentifier {
          override val eventName: String = "TestItem"
        
          override val uniqueIdentifier: Int = 0
        
          override val menuItem: String = "item1"
        
          override val menuType: String = "Item"
        
          override val screenName: String? = null
        }
        
        public object TestItem2Event : MenuItemEventIdentifier {
          override val eventName: String = "TestItem2"
        
          override val uniqueIdentifier: Int = 1
        
          override val menuItem: String = "item2"
        
          override val menuType: String = "Toolbar"
        
          override val screenName: String? = null
        }

    """.trimIndent()
}