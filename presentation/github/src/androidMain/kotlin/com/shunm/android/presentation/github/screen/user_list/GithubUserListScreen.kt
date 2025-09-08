package com.shunm.android.presentation.github.screen.user_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.appbar.ClSmallAppbar
import com.shunm.android.presentation.component.list.Headline
import com.shunm.android.presentation.component.list.LeadingImage
import com.shunm.android.presentation.component.list.ListItem
import com.shunm.android.presentation.shared.component.ClScaffold

@Composable
internal fun GithubUserListScreen(
    uiState: GithubUserListScreen.GithubUserListUiState,
) {
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
                ListItem(
                    leading = {
                        LeadingImage(
                            url = user.avatarUrl,
                            contentDescription = user.login,
                        )
                    }
                ) {
                    oneLine(
                        headline = {
                            Headline(user.login)
                        },
                    )
                }
            }
        }
    }
}
