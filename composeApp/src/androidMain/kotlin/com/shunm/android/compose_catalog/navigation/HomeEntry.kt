package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.shunm.android.compose_catalog.screen.home.HomeScreen
import com.shunm.android.compose_catalog.screen.home.HomeScreenUiStateHolder
import com.shunm.android.presentation.component.navigation.CatalogTypeListRoute
import com.shunm.android.presentation.github.navigation.GithubUserListRoute
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavRoute

fun <T : Any> EntryProviderBuilder<T>.homeEntry(
    navController: NavController,
) {
    entry<HomeRoute> {
        val uiStateHolder = viewModel<HomeScreenUiStateHolder>()
        val uiState by uiStateHolder.HomeScreenPresenter(
            onNavigateToCatalog = {
                navController.navigate(CatalogTypeListRoute)
            },
            onNavigateToGithubUserList = {
                navController.navigate(GithubUserListRoute)
            }
        )
        HomeScreen(
            uiState = uiState
        )
    }
}
