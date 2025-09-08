package com.shunm.android.compose_catalog.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shunm.android.presentation.shared.ui_state.UiState
import com.shunm.android.presentation.shared.ui_state.UiStateAggregation
import com.shunm.android.presentation.shared.ui_state.UiStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data object HomeScreen {

    sealed interface HomeScreenUiState : UiStateAggregation<HomeScreenUiState.Loading, HomeScreenUiState.Success, HomeScreenUiState.Error> {
        val eventSink: (HomeScreenEvent) -> Unit

        data object Error : HomeScreenUiState, UiState.Error {
            override val eventSink: (HomeScreenEvent) -> Unit = {}
        }

        data object Loading : HomeScreenUiState, UiState.Loading {
            override val eventSink: (HomeScreenEvent) -> Unit = {}
        }

        data class Success(
            override val eventSink: (HomeScreenEvent) -> Unit
        ) : HomeScreenUiState, UiState.Success
    }

    sealed interface HomeScreenEvent {
        data object NavigateToCatalog : HomeScreenEvent
        data object NavigateToGithubUserList : HomeScreenEvent
    }
}

@HiltViewModel
internal class HomeScreenUiStateHolder @Inject constructor() : ViewModel(), UiStateHolder {

    private val uiStateFlow = MutableStateFlow<HomeScreen.HomeScreenUiState>(
        HomeScreen.HomeScreenUiState.Success(eventSink = {})
    )

    @Composable
    fun HomeScreenPresenter(
        onNavigateToCatalog: () -> Unit,
        onNavigateToGithubUserList: () -> Unit
    ): State<HomeScreen.HomeScreenUiState> {

        val eventSink = remember {
            { event: HomeScreen.HomeScreenEvent ->
                when (event) {
                    is HomeScreen.HomeScreenEvent.NavigateToCatalog -> {
                        onNavigateToCatalog()
                    }

                    is HomeScreen.HomeScreenEvent.NavigateToGithubUserList -> {
                        onNavigateToGithubUserList()
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            uiStateFlow.value = HomeScreen.HomeScreenUiState.Success(eventSink = eventSink)
        }

        return uiStateFlow.collectAsStateWithLifecycle()
    }
}
