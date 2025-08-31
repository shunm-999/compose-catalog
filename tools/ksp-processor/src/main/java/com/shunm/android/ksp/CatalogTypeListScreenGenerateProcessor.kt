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
            section {
                "import androidx.compose.foundation.clickable".l()
                "import androidx.compose.foundation.layout.Column".l()
                "import androidx.compose.material3.ListItem".l()
                "import androidx.compose.material3.Text".l()
                "import androidx.compose.runtime.Composable".l()
                "import androidx.compose.ui.Modifier".l()
            }

            // CatalogType enum class
            section {
                "enum class CatalogType {".l {
                    for (key in catalogMap.entries().keys) {
                        "$key,".l()
                    }
                }
                "}".l()
            }

            // add CatalogTypeScreen
            section {
                "@Composable".l()
                "fun CatalogTypeScreen(".l {
                    "onClick: (CatalogType) -> Unit,".l()
                }
                ") {".l {
                    "Column {".l {
                        "for (catalogType in CatalogType.entries) {".l {
                            "CatalogTypeItem(".l {
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
            }

            // add CatalogTypeItem
            section {
                "@Composable".l()
                "fun CatalogTypeItem(".l {
                    "catalogType: CatalogType,".l()
                    "onClick: () -> Unit,".l()
                }
                ") {".l {
                    "ListItem(".l {
                        "headlineContent = { Text(text = catalogType.name) },".l()
                        "modifier = Modifier.clickable { onClick() }".l()
                    }
                    ")".l()
                }
                "}".l()
            }
        }
    }
}
