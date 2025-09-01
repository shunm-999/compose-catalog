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
                "import androidx.compose.foundation.layout.padding".l()
                "import androidx.compose.material.icons.Icons".l()
                "import androidx.compose.material.icons.automirrored.outlined.ArrowRight".l()
                "import androidx.compose.material3.CenterAlignedTopAppBar".l()
                "import androidx.compose.material3.Icon".l()
                "import androidx.compose.material3.IconButton".l()
                "import androidx.compose.material3.Scaffold".l()
                "import androidx.compose.material3.ListItem".l()
                "import androidx.compose.material3.Text".l()
                "import androidx.compose.runtime.Composable".l()
                "import androidx.compose.ui.Modifier".l()
                "import org.jetbrains.compose.ui.tooling.preview.Preview".l()
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
                "fun CatalogTypeListScreen(".l {
                    "onClick: (CatalogType) -> Unit,".l()
                }
                ") {".l {
                    "Scaffold(".l {
                        " topBar = {".l {
                            "CenterAlignedTopAppBar(".l {
                                " title = {".l {
                                    "Text(text = \"Catalog\")".l()
                                }
                                "}".l()
                            }
                            ")".l()
                        }
                        "}".l()
                    }
                    ") { paddingValues ->".l {
                        "Column(modifier = Modifier.padding(paddingValues)) {".l {
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
                        "trailingContent = { IconButton(onClick = onClick) { Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowRight, contentDescription = null) } },".l()
                        "modifier = Modifier.clickable { onClick() }".l()
                    }
                    ")".l()
                }
                "}".l()
            }

            // add Preview
            section {
                "@Preview".l()
                "@Composable".l()
                "fun PreviewCatalogTypeListScreen() {".l {
                    "CatalogTypeListScreen(onClick = {})".l()
                }
                "}".l()
            }
        }
    }
}
