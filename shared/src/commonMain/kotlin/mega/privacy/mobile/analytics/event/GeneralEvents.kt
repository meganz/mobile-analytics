package mega.privacy.mobile.analytics.event

import mega.privacy.mobile.analytics.annotations.GeneralEvent

@GeneralEvent
class Example(
    val foo: String?,
    val bar: Int,
){
    val fooBar = 22
    val barFoo = "22"
}
