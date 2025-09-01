package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies

class CodeBuilder(
    private val dependencies: MutableList<String> = mutableListOf(),
    private val level: Int = 0,
    private val stringBuilder: StringBuilder,
) {

    fun dependency(name: String?) {
        if (name == null) {
            return
        }
        if (!dependencies.contains(name)) {
            dependencies += name
        }
    }

    fun dependencies(vararg names: String?) {
        names.forEach { dependency(it) }
    }

    fun String.l(block: CodeBuilder.() -> Unit = {}) {
        stringBuilder.append(" ".repeat(level))
        stringBuilder.appendLine(this)

        CodeBuilder(
            dependencies = dependencies,
            level = level + 4,
            stringBuilder = stringBuilder,
        ).apply(block)
    }

    fun section(block: CodeBuilder.() -> Unit = {}) {
        this.block()
        stringBuilder.appendLine("")
    }

    fun indent(block: CodeBuilder.() -> Unit = {}) {
        CodeBuilder(
            dependencies = dependencies,
            level = level + 4,
            stringBuilder = stringBuilder,
        ).apply(block)
    }

    fun indentOrNot(condition: Boolean, block: CodeBuilder.() -> Unit = {}) {
        if (condition) {
            indent(block)
        } else {
            this.block()
        }
    }

    fun build(): String = buildString {
        val dependencies = dependencies.distinct().sorted()
        for (dependency in dependencies) {
            appendLine("import $dependency")
        }
        appendLine("")
        append(stringBuilder.toString())
    }
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
