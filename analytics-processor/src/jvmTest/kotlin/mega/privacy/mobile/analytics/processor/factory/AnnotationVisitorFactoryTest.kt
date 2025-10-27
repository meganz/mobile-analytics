package mega.privacy.mobile.analytics.processor.factory

import com.google.common.truth.Truth.assertThat
import mega.privacy.mobile.analytics.annotations.ButtonPressEvent
import mega.privacy.mobile.analytics.annotations.DialogDisplayedEvent
import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.GestureEvent
import mega.privacy.mobile.analytics.annotations.ItemSelectedEvent
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.annotations.MenuItemEvent
import mega.privacy.mobile.analytics.annotations.NavigationEvent
import mega.privacy.mobile.analytics.annotations.NotificationEvent
import mega.privacy.mobile.analytics.annotations.ScreenViewEvent
import mega.privacy.mobile.analytics.annotations.TabSelectedEvent
import mega.privacy.mobile.analytics.processor.visitor.ButtonPressVisitor
import mega.privacy.mobile.analytics.processor.visitor.DialogDisplayedEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.GeneralEventVisitor
import mega.privacy.mobile.analytics.processor.visitor.GestureEventVisitor
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
class AnnotationVisitorFactoryTest {
    private val underTest = AnnotationVisitorFactory(idGeneratorFactory = IdGeneratorFactory())

    @ParameterizedTest
    @MethodSource("visitorParams")
    fun `test that correct types are returned`(eventClass: KClass<*>, expected: Class<*>) {
        assertThat(underTest(eventClass)).isInstanceOf(expected)
    }

    private fun visitorParams() = Stream.of(
        Arguments.of(ScreenViewEvent::class, ScreenViewVisitor::class.java),
        Arguments.of(TabSelectedEvent::class, TabSelectedVisitor::class.java),
        Arguments.of(GeneralEvent::class, GeneralEventVisitor::class.java),
        Arguments.of(ButtonPressEvent::class, ButtonPressVisitor::class.java),
        Arguments.of(ItemSelectedEvent::class, ItemSelectedEventVisitor::class.java),
        Arguments.of(MenuItemEvent::class, MenuItemEventVisitor::class.java),
        Arguments.of(NavigationEvent::class, NavigationEventVisitor::class.java),
        Arguments.of(NotificationEvent::class, NotificationEventVisitor::class.java),
        Arguments.of(DialogDisplayedEvent::class, DialogDisplayedEventVisitor::class.java),
        Arguments.of(LegacyEvent::class, LegacyEventVisitor::class.java),
        Arguments.of(GestureEvent::class, GestureEventVisitor::class.java),
    )
}