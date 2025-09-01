package com.shunm.android.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration

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
                "androidx.compose.foundation.layout.padding",
                "androidx.compose.foundation.rememberScrollState",
                "androidx.compose.foundation.verticalScroll",
                "androidx.compose.runtime.Composable",
                "androidx.compose.ui.Modifier",
                "androidx.compose.ui.unit.dp",
                "com.shunm.android.presentation.component.di.NullableProvider",
                "org.jetbrains.compose.ui.tooling.preview.Preview",
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

    private fun CodeBuilder.generateCatalog(
        key: String,
        catalogs: List<KSFunctionDeclaration>,
        providers: List<KSClassDeclaration>,
    ) {
        section {
            "@Composable".l()
            "fun ${key}Catalog() {".l {
                "Column(".l {
                    "modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).verticalScroll(rememberScrollState()),".l()
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
        val qualifiedName = catalog.qualifiedName?.getQualifier()
        if (qualifiedName != null) {
            dependencies(qualifiedName)
        }

        val params = catalog.parameters
        "${catalog.simpleName}(".l {
            for (param in params) {
                val provider = providers.find {
                    val genericType = it.genericTypeArgOrNull(resolver, PROVIDER_FQ_NAME) ?: return@find false
                    genericType.equalsIgnoringNullabilityDeep(
                        resolver = resolver,
                        other = param.type.resolve()
                    )
                }
                if (provider == null) {
                    logger.error(
                        message = "No provider found for parameter: ${param.name?.asString()} of type ${param.type.resolve().declaration.qualifiedName?.asString()} in ${catalog.simpleName.asString()}",
                        symbol = catalog,
                    )
                    throw IllegalStateException("No provider found for parameter: ${param.name?.asString()} of type ${param.type.resolve().declaration.qualifiedName?.asString()} in ${catalog.simpleName.asString()}")
                }

                if (param.type.resolve().isDeepNullable()) {
                    // TODO for文の導入
                    "NullableProvider(${provider.simpleName.asString()}()).provide()[0],"
                } else {
                    "${provider.simpleName.asString()}.provide()[0],"
                }
            }
        }
        ")".l()
    }
}
