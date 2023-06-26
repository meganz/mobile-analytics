package mega.privacy.mobile.analytics.processor.generator.parameter

import com.tschuchort.compiletesting.SourceFile

interface GeneratorCodeTestParameter {
    val sourceFile: SourceFile
    val expectedOutput: String
    val outPutFileName: String
}