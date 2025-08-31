package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies

internal class CatalogTypeListScreenGenerateProcessor(
    private val codeGenerator: CodeGenerator,
    private val catalogMap: CatalogMap,
) {

    fun generate() {
        generateFile(
            codeGenerator = codeGenerator,
            dependencies = Dependencies(false),
            packageName = "com.shunm.android.presentation.component",
            fileName = "CatalogTypeListScreen",
        ) {
            // add imports
            appendLine("import androidx.compose.foundation.clickable")
            appendLine("import androidx.compose.foundation.layout.Column")
            appendLine("import androidx.compose.material3.ListItem")
            appendLine("import androidx.compose.material3.Text")
            appendLine("import androidx.compose.runtime.Composable")
            appendLine("import androidx.compose.ui.Modifier")
            appendLine("import org.jetbrains.compose.ui.tooling.preview.Preview")

            // CatalogType enum class
            appendLine("enum class CatalogType {")
            withIndent {
                for (key in catalogMap.entries().keys) {
                    appendLine("$key,")
                }
            }
            appendLine("}")

            // add CatalogTypeScreen
            appendLine("@Composable")
            appendLine("fun CatalogTypeScreen(")
            withIndent {
                appendLine("onClick: (CatalogType) -> Unit,")
            }
            appendLine(") {")
            withIndent {
                appendLine("Column {")
                withIndent {
                    appendLine("for (catalogType in CatalogType.entries) {")
                    withIndent {
                        appendLine("CatalogTypeItem(")
                        appendLine("catalogType = catalogType,")
                        appendLine("onClick = { onClick(catalogType) }")
                        appendLine(")")
                    }
                    appendLine("}")
                }
                appendLine("}")
            }
            appendLine("}")
        }
    }
}
