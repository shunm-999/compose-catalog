package com.shunm.android.presentation.shared.ui_state

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
actual fun <T : UiStateHolder> uiStateHolderProvider(): T = viewModel()
