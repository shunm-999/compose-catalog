package com.shunm.android.presentation.component.navigation

import CatalogTypeListScreen
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object CatalogTypeListRoute : NavRoute

fun <T : Any> EntryProviderBuilder<T>.componentEntry(navController: NavController) {
    entry<CatalogTypeListRoute> {
        CatalogTypeListScreen { type ->
        }
    }
}
