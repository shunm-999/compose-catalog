package com.shunm.android.presentation.shared.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shunm.android.presentation.shared.ui_state.UiState
import com.shunm.android.presentation.shared.ui_state.UiStateAggregation

@Composable
inline fun <reified L : UiState.Loading, reified S : UiState.Success, reified E : UiState.Error> ClScaffold(
    uiState: UiStateAggregation<L, S, E>,
    crossinline topAppbar: @Composable () -> Unit = { },
    crossinline loading: @Composable (uiState: L) -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    },
    crossinline error: @Composable (uiState: E) -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Error occurred")
        }
    },
    crossinline content: @Composable (uiState: S) -> Unit,
) {
    Scaffold(
        topBar = {
            topAppbar()
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
        ) {
            when (uiState) {
                is L -> {
                    loading(uiState)
                }

                is E -> {
                    error(uiState)
                }

                is S -> {
                    content(uiState)
                }
            }
        }
    }
}
