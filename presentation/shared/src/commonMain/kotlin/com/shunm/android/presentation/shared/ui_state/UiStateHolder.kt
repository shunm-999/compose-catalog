package com.shunm.android.presentation.shared.ui_state

import androidx.compose.runtime.Composable

interface UiStateHolder

interface UiState {
    interface Error : UiState
    interface Loading : UiState
    interface Success : UiState
}

interface UiStateAggregation<L : UiState.Loading, S : UiState.Success, E : UiState.Error> : UiState
