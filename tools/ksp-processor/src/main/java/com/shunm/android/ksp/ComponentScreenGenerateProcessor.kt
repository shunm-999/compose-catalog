package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration

class ComponentScreenGenerateProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        println("⭐️ 処理きた")
        if (invoked) {
            return emptyList()
        }
        invoked = true

        val catalogables = resolver.getSymbolsWithAnnotation(CATALOGALBE_ANNOTATION_FQ_NAME)
            .filterIsInstance<KSFunctionDeclaration>()
        for (catalogable in catalogables) {
            println("⭐️ $catalogable")
        }
        return emptyList()
    }

    private companion object {
        private const val CATALOGALBE_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.Catalogable"
        private const val PROVIDES_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.CatalogProvider"
    }
}
