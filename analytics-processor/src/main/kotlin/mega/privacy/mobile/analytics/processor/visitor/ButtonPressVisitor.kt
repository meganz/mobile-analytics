package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import mega.privacy.mobile.analytics.processor.visitor.response.EventResponse

class ButtonPressVisitor: KSDefaultVisitor<EventData, EventResponse>() {
    override fun defaultHandler(node: KSNode, data: EventData): EventResponse {
        TODO("Not yet implemented")
    }
}