package com.shunm.android.presentation.github.screen.user_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.shunm.android.domain.github.model.GithubUserId
import com.shunm.android.presentation.component.appbar.ClSmallAppbar
import com.shunm.android.presentation.component.list.ListItem
import com.shunm.android.presentation.shared.component.ClScaffold
import com.shunm.android.presentation.shared.ui_state.viewModelProvider

@Composable
internal fun GithubUserListScreen(
    uiStateHolder: GithubUserListUiStateHolder = viewModelProvider(),
    onBack: () -> Unit,
    onClickGithubUser: (GithubUserId) -> Unit
) {
    val uiState by uiStateHolder.GithubUserListScreenPresenter(
        onBack = onBack,
        onClickGithubUser = onClickGithubUser
    )
    ClScaffold(
        uiState = uiState,
        topAppbar = {
            ClSmallAppbar(
                headline = {
                    Text("Github Users")
                }
            )
        }
    ) { uiState ->
        LazyColumn {
            items(uiState.users) { user ->
                ListItem {
                    twoLine(
                        headline = {
                            Text(user.login)
                        },
                        supportingText = {
                            Text(user.name ?: "")
                        }
                    )
                }
            }
        }
    }
}
