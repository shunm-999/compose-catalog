package com.shunm.android.compose_catalog.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavRoute

fun  <T : Any> EntryProviderBuilder<T>.homeEntry(navController: NavController) {
    entry<HomeRoute> {

    }
}
