package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSClassDeclaration
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateSimpleIdRequest
import mega.privacy.mobile.analytics.processor.visitor.data.EventData
import kotlin.reflect.KClass

/**
 * Simple id analytics event visitor
 *
 * @param idGenerator
 * @param eventIdentifierClass
 */
abstract class SimpleIdAnalyticsEventVisitor(
    idGenerator: IdGenerator<GenerateSimpleIdRequest>,
    eventIdentifierClass: KClass<*>,
) : AnalyticsEventVisitor<GenerateSimpleIdRequest>(
    idGenerator, eventIdentifierClass
) {

    override fun getIdGeneratorParam(
        classDeclaration: KSClassDeclaration,
        data: EventData,
    ) = GenerateSimpleIdRequest(
        eventName = getAnnotatedClassName(classDeclaration),
        currentIdMap = data.idMap
    )
}