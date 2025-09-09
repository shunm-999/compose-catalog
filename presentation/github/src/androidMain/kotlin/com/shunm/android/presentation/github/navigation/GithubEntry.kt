package com.shunm.android.presentation.github.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.shunm.android.presentation.github.screen.user_list.GithubUserListScreen
import com.shunm.android.presentation.github.screen.user_list.GithubUserListUiStateHolder
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object GithubUserListRoute : NavRoute

fun <T : Any> EntryProviderBuilder<T>.githubEntry(
    navController: NavController,
) {
    entry<GithubUserListRoute> {
        val uiStateHolder = viewModel<GithubUserListUiStateHolder>()
        val uiState by uiStateHolder.GithubUserListScreenPresenter(
            onBack = { navController.popBackStack() },
            onClickGithubUser = {},
        )
        GithubUserListScreen(
            uiState = uiState,
        )
    }
}
