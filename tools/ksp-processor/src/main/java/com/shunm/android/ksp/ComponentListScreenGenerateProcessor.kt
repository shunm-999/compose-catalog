package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSType

internal class ComponentListScreenGenerateProcessor(
    private val codeGenerator: CodeGenerator,
    private val resolver: Resolver,
    private val logger: KSPLogger,
    private val catalogMap: CatalogMap,
    private val providers: List<KSClassDeclaration>,
) {
    private companion object Companion {
        private const val PROVIDER_FQ_NAME = "com.shunm.android.presentation.component.di.Provider"
    }

    fun generate() {
        generateFile(
            codeGenerator = codeGenerator,
            dependencies = Dependencies(false),
            packageName = "com.shunm.android.presentation.component",
            fileName = "ComponentListScreen",
        ) {
            // add imports
            dependencies(
                "androidx.compose.foundation.layout.Arrangement",
                "androidx.compose.foundation.layout.Column",
                "androidx.compose.foundation.layout.fillMaxWidth",
                "androidx.compose.foundation.layout.padding",
                "androidx.compose.foundation.rememberScrollState",
                "androidx.compose.foundation.verticalScroll",
                "androidx.compose.material.icons.Icons",
                "androidx.compose.material.icons.automirrored.filled.ArrowBack",
                "androidx.compose.material3.CenterAlignedTopAppBar",
                "androidx.compose.material3.Icon",
                "androidx.compose.material3.IconButton",
                "androidx.compose.material3.Scaffold",
                "androidx.compose.material3.Surface",
                "androidx.compose.material3.Text",
                "androidx.compose.runtime.Composable",
                "androidx.compose.ui.Modifier",
                "androidx.compose.ui.unit.dp",
                "com.shunm.android.presentation.component.di.NullableProvider",
                "org.jetbrains.compose.ui.tooling.preview.Preview",
            )

            // add switch function
            generateSwitchCatalog(
                catalogMap = catalogMap,
            )

            // add Composable function
            for ((key, catalogs) in catalogMap.entries()) {
                generateCatalog(
                    key = key,
                    catalogs = catalogs,
                    providers = providers,
                )
            }
        }
    }

    private fun CodeBuilder.generateSwitchCatalog(
        catalogMap: CatalogMap,
    ) {
        section {
            "@Composable".l()
            "fun ComponentListScreen(".l {
                "catalogType: CatalogType,".l()
                "onBack : () -> Unit = {},".l()
            }
            ") {".l {
                "Scaffold(".l {
                    "topBar = {".l {
                        "CenterAlignedTopAppBar(".l {
                            "title = {".l {
                                "Text(text = \"ComponentList : \$catalogType\")".l()
                            }
                            "},".l()
                            "navigationIcon = {".l {
                                "IconButton(".l {
                                    "onClick = {".l {
                                        "onBack()".l()
                                    }
                                    "}".l()
                                }
                                ") {".l {
                                    "Icon(Icons.AutoMirrored.Default.ArrowBack, null)".l()
                                }
                                "}".l()
                            }
                            "}".l()
                        }
                        ")".l()
                    }
                    "}".l()
                }
                ") { paddingValues ->".l {
                    "Surface(modifier = Modifier.padding(paddingValues)) {".l {
                        "when (catalogType) {".l {
                            for (catalogType in catalogMap.entries().keys) {
                                "CatalogType.$catalogType -> ${catalogType}Catalog()".l()
                            }
                        }
                        "}".l()
                    }
                    "}".l()
                }
                "}".l()
            }
            "}".l()
        }
    }

    private fun CodeBuilder.generateCatalog(
        key: String,
        catalogs: List<KSFunctionDeclaration>,
        providers: List<KSClassDeclaration>,
    ) {
        section {
            "@Composable".l()
            "fun ${key}Catalog() {".l {
                "Column(".l {
                    "modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp).verticalScroll(rememberScrollState()),".l()
                    "verticalArrangement = Arrangement.spacedBy(8.dp),".l()
                }
                ") {".l {
                    for (catalog in catalogs) {
                        generateCallComposable(
                            catalog = catalog,
                            providers = providers,
                        )
                    }
                }
                "}".l()
            }
            "}".l()
        }
    }

    private fun CodeBuilder.generateCallComposable(
        catalog: KSFunctionDeclaration,
        providers: List<KSClassDeclaration>,
    ) {
        val qualifiedName = catalog.qualifiedName?.asString()
        if (qualifiedName != null) {
            dependencies(qualifiedName)
        }

        val functionParams = getFunctionParams(
            catalog = catalog,
            providers = providers,
        )
        generateCallComposableImpl(
            count = 0,
            catalog = catalog,
            functionParams = functionParams,
        )
    }

    private fun CodeBuilder.generateCallComposableImpl(
        count: Int = 0,
        catalog: KSFunctionDeclaration,
        functionParams: List<FunctionParam>,
    ) {
        if (functionParams.isEmpty()) {
            "${catalog.simpleName.asString()}()".l()
            return
        }
        val functionParam = functionParams[count]
        val ch = ('a'.code + count).toChar()

        val provider = functionParam.provider
        val forStatementOpt = if (functionParam.type.isDeepNullable()) {
            if (provider != null) {
                "for ($ch in NullableProvider(${functionParam.provider.simpleName.asString()}()).provide()) {"
            } else {
                null
            }
        } else {
            if (provider != null) {
                "for ($ch in ${functionParam.provider.simpleName.asString()}().provide()) {"
            } else {
                null
            }
        }

        forStatementOpt?.l()
        indentOrNot(forStatementOpt != null) {
            if (count >= functionParams.size - 1) {
                "${catalog.simpleName.asString()}(".l {
                    functionParams.forEachIndexed { index, param ->
                        if (param.provider != null) {
                            "${param.name.asString()} = ${('a'.code + index).toChar()},".l()
                        }
                    }
                }
                ")".l()
            } else {
                generateCallComposableImpl(
                    count = count + 1,
                    catalog = catalog,
                    functionParams = functionParams,
                )
            }
        }
        if (forStatementOpt != null) {
            "}".l()
        }
    }

    private fun CodeBuilder.getFunctionParams(
        catalog: KSFunctionDeclaration,
        providers: List<KSClassDeclaration>,
    ): List<FunctionParam> = catalog.parameters.map { param ->
        val provider = providers.find {
            val genericType = it.genericTypeArgOrNull(resolver, PROVIDER_FQ_NAME) ?: return@find false
            genericType.equalsIgnoringNullabilityDeep(
                resolver = resolver,
                other = param.type.resolve(),
            )
        }
        dependencies("com.shunm.android.presentation.component.di.NullableProvider")
        dependencies(provider?.qualifiedName?.asString())

        FunctionParam(
            name = param.name!!,
            type = param.type.resolve(),
            provider = provider,
        )
    }
}

private data class FunctionParam(
    val name: KSName,
    val type: KSType,
    val provider: KSClassDeclaration?,
)
