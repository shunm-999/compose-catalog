package com.shunm.android.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ComponentScreenGenerateProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return ComponentScreenGenerateProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger,
        )
    }
}
