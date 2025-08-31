package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.util.Locale

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


        val catalogables = resolver.getSymbolsWithAnnotation(CATALOGALBE_ANNOTATION_FQ_NAME)
            .filterIsInstance<KSFunctionDeclaration>()

        println("⭐️ Found catalogables: ${catalogables.count()}")

        val catalogMap = CatalogMap().apply {
            addAll(catalogables.toList())
        }

        generateFile(
            codeGenerator = codeGenerator,
            dependencies = Dependencies(false),
            packageName = "com.shunm.android.presentation.component",
            fileName = "ComponentCatalogScreen",
        ) {
            append("enum class CatalogType {")
            withIndent {
                for (key in catalogMap.entries().keys) {
                    append("$key,")
                }
            }
            append("}")
        }

        return emptyList()
    }

    private companion object {
        private const val CATALOGALBE_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.Catalogable"
        private const val PROVIDES_ANNOTATION_FQ_NAME = "com.shunm.android.presentation.component.di.CatalogProvider"
    }
}

private class CatalogMap {
    private val internalMap = mutableMapOf<String, MutableList<KSFunctionDeclaration>>()

    private fun String.capitalizeFirstChar(): String =
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    fun add(function: KSFunctionDeclaration) {
        val folderName = function.packageName.getShortName().capitalizeFirstChar()
        internalMap.getOrPut(folderName) { mutableListOf() }.add(function)
    }

    fun addAll(functions: List<KSFunctionDeclaration>) {
        functions.forEach { add(it) }
    }

    fun entries(): Map<String, List<KSFunctionDeclaration>> = internalMap
}
