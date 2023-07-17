package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class NotificationEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "NotificationEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.NotificationEvent

@NotificationEvent
interface TestNotification
    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event
    
        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.NotificationEventIdentifier
        
        public object TestNotificationEvent : NotificationEventIdentifier {
          override val eventName: String = "TestNotification"
        
          override val uniqueIdentifier: Int = 0
        }

    """.trimIndent()
}