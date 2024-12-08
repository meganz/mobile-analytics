package mega.privacy.mobile.analytics.processor.factory

import com.google.common.truth.Truth.assertThat
import mega.privacy.mobile.analytics.processor.identifier.LegacyIdGenerator
import mega.privacy.mobile.analytics.processor.identifier.SingleRangeIdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateIdRequest
import mega.privacy.mobile.analytics.processor.visitor.AnalyticsEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ButtonPressVisitor
import mega.privacy.mobile.analytics.processor.visitor.DialogDisplayedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ItemSelectedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.LegacyEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.MenuItemEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.NavigationEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.NotificationEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.ScreenViewVisitor
import mega.privacy.mobile.analytics.processor.visitor.TabSelectedVisitor
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IdGeneratorFactoryTest {
    private val underTest: IdGeneratorFactory = IdGeneratorFactory()

    @ParameterizedTest
    @MethodSource("generatorParams")
    fun `test that correct types are returned`(
        visitorClass: KClass<out AnalyticsEventVisitor<GenerateIdRequest>>,
        expected: Class<*>,
    ) {
        assertThat(underTest(visitorClass)).isInstanceOf(expected)
    }

    private fun generatorParams() = Stream.of(
        Arguments.of(ScreenViewVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(TabSelectedVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(GeneralEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(ButtonPressVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(ItemSelectedEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(MenuItemEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(NavigationEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(NotificationEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(DialogDisplayedEventVisitor::class, SingleRangeIdGenerator::class.java),
        Arguments.of(LegacyEventVisitor::class, LegacyIdGenerator::class.java),
    )
}