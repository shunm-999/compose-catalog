package com.shunm.android.compose_catalog

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.shunm.android.compose_catalog.navigation.NavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavHost()
    }
}