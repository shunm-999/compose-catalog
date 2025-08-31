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
            "import androidx.compose.foundation.clickable".l()
            "import androidx.compose.foundation.layout.Column".l()
            "import androidx.compose.material3.ListItem".l()
            "import androidx.compose.material3.Text".l()
            "import androidx.compose.runtime.Composable".l()
            "import androidx.compose.ui.Modifier".l()
            "".l()

            // CatalogType enum class
            "enum class CatalogType {".l()
            ind {
                for (key in catalogMap.entries().keys) {
                    "$key,".l()
                }
            }
            "}".l()
            "".l()

            // add CatalogTypeScreen
            "@Composable".l()
            "fun CatalogTypeScreen(".l()
            ind {
                "onClick: (CatalogType) -> Unit,".l()
            }
            ") {".l()
            ind {
                "Column {".l()
                ind {
                    "for (catalogType in CatalogType.entries) {".l()
                    ind {
                        "CatalogTypeItem(".l()
                        ind {
                            "catalogType = catalogType,".l()
                            "onClick = { onClick(catalogType) }".l()
                        }
                        ")".l()
                    }
                    "}".l()
                }
                "}".l()
            }
            "}".l()
            "".l()

            // add CatalogTypeItem
            "@Composable".l()
            "fun CatalogTypeItem(".l()
            ind {
                "catalogType: CatalogType,".l()
                "onClick: () -> Unit,".l()
            }
            ") {".l()
            ind {
                "ListItem(".l()
                ind {
                    "headlineContent = { Text(text = catalogType.name) },".l()
                    "modifier = Modifier.clickable { onClick() }".l()
                }
                ")".l()
            }
            "}".l()
            "".l()
        }
    }
}
