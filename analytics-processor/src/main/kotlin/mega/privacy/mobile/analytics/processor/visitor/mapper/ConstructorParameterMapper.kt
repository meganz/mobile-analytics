package mega.privacy.mobile.analytics.processor.visitor.mapper

import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ksp.toTypeName

/**
 * Param spec mapper
 */
class ConstructorParameterMapper {
    /**
     * Invoke
     *
     * @param valueParameter
     */
    operator fun invoke(valueParameter: KSValueParameter) =
        valueParameter.name?.getShortName()?.let {
            val type = valueParameter.type.toTypeName()
            ParameterSpec(it, type)
        }
}