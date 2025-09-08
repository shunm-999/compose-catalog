package com.shunm.android.compose_catalog.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.shunm.android.presentation.component.appbar.ClSmallAppbar
import com.shunm.android.presentation.component.list.Headline
import com.shunm.android.presentation.component.list.ListItem
import com.shunm.android.presentation.shared.component.ClScaffold
import com.shunm.android.presentation.shared.ext.items
import com.shunm.android.presentation.shared.ui_state.uiStateHolderProvider

enum class HomeScreenContent {
    Catalog,
    GithubUserList
}

@Composable
internal fun HomeScreen(
    uiStateHolder: HomeScreenUiStateHolder = uiStateHolderProvider(),
    onNavigateToCatalog : () -> Unit = {},
    onNavigateToGithubUserList : () -> Unit = {}
) {
    val uiState by uiStateHolder.HomeScreenPresenter(
        onNavigateToCatalog = {
            onNavigateToCatalog()
        },
        onNavigateToGithubUserList = {
            onNavigateToGithubUserList()
        }
    )
    ClScaffold(
        uiState = uiState,
        topAppbar = {
            TopAppBar()
        }
    ) {
        Column {
            items(HomeScreenContent.entries) { content ->
                ListItem(
                    onClick = {
                        when (content) {
                            HomeScreenContent.Catalog -> {
                                uiState.eventSink(HomeScreen.HomeScreenEvent.NavigateToCatalog)
                            }

                            HomeScreenContent.GithubUserList -> {
                                uiState.eventSink(HomeScreen.HomeScreenEvent.NavigateToGithubUserList)
                            }
                        }
                    }
                ) {
                    oneLine {
                        Headline(content.name)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar() {
    ClSmallAppbar(
        headline = {
            Text("Home")
        }
    )
}
