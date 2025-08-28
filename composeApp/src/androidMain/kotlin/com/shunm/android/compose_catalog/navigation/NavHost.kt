package com.shunm.android.compose_catalog.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
actual fun NavHost() {
    NavHost(
        startDestination = CategoryListRoute,
        onBack = { currentRoute ->
            popBackStack()
        },
        entryProvider = {
            entryProvider {
                entry<CategoryListRoute> {
                    Scaffold { paddingValues ->
                        Column(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            Button(
                                onClick = {
                                    navigate(ComponentListRoute)
                                }
                            ) {
                                Text("To Component List")
                            }
                        }
                    }
                }
                entry<ComponentListRoute> {
                    Scaffold { paddingValues ->
                        Column(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            Button(
                                onClick = {
                                    navigate(CategoryListRoute)
                                }
                            ) {
                                Text("To Component Detail")
                            }
                        }
                    }
                }
                entry<ComponentDetailRoute> { key ->
                    Scaffold { paddingValues ->
                        Column(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            Button(
                                onClick = {
                                    popBackStack()
                                }
                            ) {
                                Text("Back to Component List")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun NavHost(
    startDestination: NavRoute,
    onBack: NavController.(currentRoute: NavRoute?) -> Unit = {},
    entryProvider: NavController.() -> (key: NavRoute) -> NavEntry<NavRoute>
) {
    val backStack = remember { mutableStateListOf(startDestination) }
    val navController = rememberNavController(backStack)

    NavDisplay(
        backStack = backStack,
        onBack = {
            onBack(navController, backStack.lastOrNull())
        },
        entryProvider = entryProvider(navController)
    )
}