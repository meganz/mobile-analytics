package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class LegacyEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "LegacyEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.LegacyEvent

@LegacyEvent(eventId = 11)
interface LegacyEvent1

@LegacyEvent(eventId = 22)
interface LegacyEvent2

@LegacyEvent(eventId = 33)
interface LegacyEvent3

    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event

        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier

        public object LegacyEvent1Event : LegacyEventIdentifier {
          override val eventName: String = "LegacyEvent1"

          override val uniqueIdentifier: Int = 11
        }

        public object LegacyEvent2Event : LegacyEventIdentifier {
          override val eventName: String = "LegacyEvent2"

          override val uniqueIdentifier: Int = 22
        }

        public object LegacyEvent3Event : LegacyEventIdentifier {
          override val eventName: String = "LegacyEvent3"

          override val uniqueIdentifier: Int = 33
        }

    """.trimIndent()
}