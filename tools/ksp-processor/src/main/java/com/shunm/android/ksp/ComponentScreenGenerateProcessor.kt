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
        if (invoked) {
            return emptyList()
        }
        invoked = true

        val catalogMap = CatalogMap()

        val catalogables = resolver.getSymbolsWithAnnotation(CATALOGALBE_ANNOTATION_FQ_NAME)
            .filterIsInstance<KSFunctionDeclaration>()
        catalogMap.addAll(catalogables.toList())
        return emptyList()
    }

    private companion object {
        private const val CATALOGALBE_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.Catalogable"
        private const val PROVIDES_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.CatalogProvider"
    }
}

private class CatalogMap {
    private val internalMap = mutableMapOf<String, MutableList<KSFunctionDeclaration>>()

    fun add(function: KSFunctionDeclaration) {
        val folderName = function.packageName.toString().split(".").last()
        internalMap.getOrPut(folderName) { mutableListOf() }.add(function)
    }

    fun addAll(functions: List<KSFunctionDeclaration>) {
        functions.forEach { add(it) }
    }

    fun values(): Map<String, List<KSFunctionDeclaration>> = internalMap
}
