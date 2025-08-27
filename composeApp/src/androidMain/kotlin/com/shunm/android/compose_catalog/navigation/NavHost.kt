package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
actual fun NavHost() {
    val backStack = remember { mutableStateListOf<NavRoute>() }
}

@Composable
private fun NavHost(
    startDestination: NavRoute,
    content: NavController.() -> Unit
) {
    val backStack = remember { mutableStateListOf(startDestination) }
}