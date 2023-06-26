package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.GeneralEvent
import mega.privacy.mobile.analytics.annotations.StaticValue

@GeneralEvent
class Example(
    val foo: String?,
    val bar: Int,
    @StaticValue("22")
    val fooBar: Int,
)
