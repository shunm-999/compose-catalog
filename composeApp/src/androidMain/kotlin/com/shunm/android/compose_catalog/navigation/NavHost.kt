package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
        entryProvider = { navController ->
            entryProvider {
                entry<CategoryListRoute> {

                }
                entry<ComponentListRoute> {

                }
                entry<ComponentDetailRoute> { key ->
                }
            }
        }
    )
}

@Composable
private fun NavHost(
    startDestination: NavRoute,
    onBack: NavController.(currentRoute: NavRoute?) -> Unit = {},
    entryProvider: (NavController) -> (key: NavRoute) -> NavEntry<NavRoute>
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