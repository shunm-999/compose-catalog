package com.shunm.android.presentation.github.screen.user_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shunm.android.domain.github.model.GithubUser
import com.shunm.android.domain.github.model.GithubUserId
import com.shunm.android.domain.github.usecase.GetGithubUserListUseCase
import com.shunm.android.domain.shared.Err
import com.shunm.android.domain.shared.Ok
import com.shunm.android.presentation.shared.ui_state.UiState
import com.shunm.android.presentation.shared.ui_state.UiStateAggregation
import com.shunm.android.presentation.shared.ui_state.UiStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data object GithubUserListScreen {
    sealed interface GithubUserListUiState : UiStateAggregation<GithubUserListUiState.Loading, GithubUserListUiState.Success, GithubUserListUiState.Error> {
        val eventSink: (GithubUserListScreenEvent) -> Unit

        data class Loading(
            override val eventSink: (GithubUserListScreenEvent) -> Unit
        ) : GithubUserListUiState, UiState.Loading

        data class Success(
            val users: List<GithubUser>,
            override val eventSink: (GithubUserListScreenEvent) -> Unit
        ) : GithubUserListUiState, UiState.Success

        data class Error(
            val message: String,
            override val eventSink: (GithubUserListScreenEvent) -> Unit
        ) : GithubUserListUiState, UiState.Error
    }

    sealed interface GithubUserListScreenEvent {
        data object OnBack : GithubUserListScreenEvent
        data class ClickGithubUser(val userId: GithubUserId) : GithubUserListScreenEvent
    }
}

@HiltViewModel
internal class GithubUserListUiStateHolder @Inject constructor(
    private val getGithubUserListUseCase: GetGithubUserListUseCase
) : ViewModel(), UiStateHolder {
    private val uiStateFlow = MutableStateFlow<GithubUserListScreen.GithubUserListUiState>(
        GithubUserListScreen.GithubUserListUiState.Loading { }
    )

    @Composable
    fun GithubUserListScreenPresenter(
        onBack: () -> Unit,
        onClickGithubUser: (GithubUserId) -> Unit
    ): State<GithubUserListScreen.GithubUserListUiState> {
        val eventSink = remember {
            { event: GithubUserListScreen.GithubUserListScreenEvent ->
                when (event) {
                    is GithubUserListScreen.GithubUserListScreenEvent.OnBack -> {
                        onBack()
                    }

                    is GithubUserListScreen.GithubUserListScreenEvent.ClickGithubUser -> {
                        onClickGithubUser(event.userId)
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            if (uiStateFlow.value !is GithubUserListScreen.GithubUserListUiState.Loading) {
                return@LaunchedEffect
            }
            uiStateFlow.value = GithubUserListScreen.GithubUserListUiState.Loading(eventSink)
            uiStateFlow.value = when (val result = getGithubUserListUseCase()) {
                is Ok -> {
                    GithubUserListScreen.GithubUserListUiState.Success(
                        users = result.value,
                        eventSink = eventSink
                    )
                }

                is Err -> {
                    GithubUserListScreen.GithubUserListUiState.Error(
                        message = result.error.message ?: "Unknown Error",
                        eventSink = eventSink
                    )
                }
            }
        }
        return uiStateFlow.collectAsStateWithLifecycle()
    }
}
