package mega.privacy.mobile.analytics.processor.visitor.response

import com.squareup.kotlinpoet.TypeSpec

/**
 * Screen view event response
 *
 * @property idMap
 * @property spec
 */
data class EventResponse(
    val idMap: Map<String, Int>,
    val spec: TypeSpec
)