package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies

class CodeBuilder(
    private val level: Int = 0,
    private val stringBuilder: StringBuilder
) {
    fun append(line: String) {
        stringBuilder.append(" ".repeat(level))
        stringBuilder.appendLine(line)
    }

    fun withIndent(block: CodeBuilder.() -> Unit) {
        CodeBuilder(level + 4, stringBuilder).apply(block)
    }

    fun build(): String = stringBuilder.toString()
}

fun generateFile(
    codeGenerator: CodeGenerator,
    dependencies: Dependencies,
    packageName: String,
    fileName: String,
    codeBuilder: CodeBuilder.() -> Unit,
) {
    val code = CodeBuilder(
        level = 0,
        stringBuilder = StringBuilder()
    ).apply(codeBuilder).build()

    val file = codeGenerator.createNewFile(
        dependencies = dependencies,
        packageName = packageName,
        fileName = fileName,
    )
    file.use {
        it.write(code.toByteArray())
    }
}
