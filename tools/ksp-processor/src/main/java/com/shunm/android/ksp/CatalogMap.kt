package com.shunm.android.ksp

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.util.Locale
import kotlin.collections.forEach

internal class CatalogMap {
    private val internalMap = mutableMapOf<String, MutableList<KSFunctionDeclaration>>()

    private fun String.capitalizeFirstChar(): String = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    fun add(function: KSFunctionDeclaration) {
        val folderName = function.packageName.getShortName().capitalizeFirstChar()
        internalMap.getOrPut(folderName) { mutableListOf() }.add(function)
    }

    fun addAll(functions: List<KSFunctionDeclaration>) {
        functions.forEach { add(it) }
    }

    fun entries(): Map<String, List<KSFunctionDeclaration>> = internalMap
}
