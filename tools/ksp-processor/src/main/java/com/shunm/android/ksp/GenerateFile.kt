package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies

class CodeBuilder(
    private val level: Int = 0,
    private val stringBuilder: StringBuilder,
) {

    fun String.l(block: CodeBuilder.() -> Unit = {}) {
        stringBuilder.append(" ".repeat(level))
        stringBuilder.appendLine(this)

        CodeBuilder(level + 4, stringBuilder).apply(block)
    }

    fun section(block: CodeBuilder.() -> Unit = {}) {
        this.block()
        stringBuilder.appendLine("")
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
        stringBuilder = StringBuilder(),
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
