package mega.privacy.mobile.analytics.processor.visitor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import mega.privacy.mobile.analytics.annotations.LegacyEvent
import mega.privacy.mobile.analytics.core.event.identifier.LegacyEventIdentifier
import mega.privacy.mobile.analytics.processor.exception.VisitorException
import mega.privacy.mobile.analytics.processor.identifier.IdGenerator
import mega.privacy.mobile.analytics.processor.identifier.model.GenerateLegacyIdRequest
import mega.privacy.mobile.analytics.processor.visitor.data.EventData

/**
 * Legacy event visitor
 */
class LegacyEventVisitor(idGenerator: IdGenerator<GenerateLegacyIdRequest>) :
    AnalyticsEventVisitor<GenerateLegacyIdRequest>(idGenerator, LegacyEventIdentifier::class) {

    override fun getIdGeneratorParam(
        classDeclaration: KSClassDeclaration,
        data: EventData,
    ): GenerateLegacyIdRequest {
        val legacyId = getLegacyIdFromAnnotation(classDeclaration)
        return GenerateLegacyIdRequest(
            legacyId = legacyId,
            eventName = getAnnotatedClassName(classDeclaration),
            currentIdMap = data.idMap,
        )
    }

    private fun getLegacyIdFromAnnotation(classDeclaration: KSClassDeclaration): Int {
        val annotation: KSAnnotation =
            classDeclaration.annotations.find { it.shortName.getShortName() == LegacyEvent::class.java.simpleName }
                ?: throw VisitorException("Expected annotation ${LegacyEvent::class.java.simpleName} not found.")
        val legacyId = annotation.getParameterValue(LegacyEvent::eventId.name) as? Int
            ?: throw IllegalArgumentException("${LegacyEvent::eventId.name} on annotation ${LegacyEvent::class.java.simpleName} is expected to be an int value")
        return legacyId
    }

}