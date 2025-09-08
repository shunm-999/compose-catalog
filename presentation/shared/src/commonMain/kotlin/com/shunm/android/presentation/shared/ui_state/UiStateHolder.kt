package com.shunm.android.presentation.shared.ui_state

import androidx.compose.runtime.Composable

interface UiStateHolder

@Composable
expect fun <T : UiStateHolder> viewModelProvider() :  T
