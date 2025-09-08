package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.shunm.android.presentation.component.navigation.CatalogTypeListRoute
import com.shunm.android.presentation.component.navigation.componentEntry
import com.shunm.android.presentation.github.navigation.githubEntry
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import com.shunm.android.presentation.shared.navigation.rememberNavController

@Composable
actual fun NavHost() {
    NavHost(
        startDestination = CatalogTypeListRoute,
        onBack = { currentRoute ->
            popBackStack()
        },
        entryProvider = {
            entryProvider {
                homeEntry(this@NavHost)
                componentEntry(this@NavHost)
                githubEntry(this@NavHost)
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
