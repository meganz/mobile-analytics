package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

class ScreenViewEventParameter : GeneratorCodeTestParameter {
    override val outPutFileName = "ScreenViewEvents.kt"

    override val sourceFile = SourceFile.kotlin(
        "input.kt",
        """
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent

@ScreenViewEvent
interface TestScreen1

@ScreenViewEvent
interface TestScreen2

@ScreenViewEvent
interface TestScreen3

    """
    )

    override val expectedOutput =
        """
        package mega.privacy.mobile.analytics.event

        import kotlin.Int
        import kotlin.String
        import mega.privacy.mobile.analytics.core.event.identifier.ScreenViewEventIdentifier

        public object TestScreen1Event : ScreenViewEventIdentifier() {
          override val eventName: String = "TestScreen1"

          override val uniqueIdentifier: Int = 0
        }

        public object TestScreen2Event : ScreenViewEventIdentifier() {
          override val eventName: String = "TestScreen2"

          override val uniqueIdentifier: Int = 1
        }

        public object TestScreen3Event : ScreenViewEventIdentifier() {
          override val eventName: String = "TestScreen3"

          override val uniqueIdentifier: Int = 2
        }

    """.trimIndent()
}