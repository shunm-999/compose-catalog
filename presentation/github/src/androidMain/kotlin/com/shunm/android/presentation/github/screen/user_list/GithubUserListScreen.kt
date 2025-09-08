package com.shunm.android.presentation.github.screen.user_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.shunm.android.domain.github.model.GithubUserId
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
}
