package com.shunm.android.presentation.component.navigation

import CatalogType
import CatalogTypeListScreen
import ComponentListScreen
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.shunm.android.presentation.shared.navigation.NavController
import com.shunm.android.presentation.shared.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
data object CatalogTypeListRoute : NavRoute

@Serializable
data class ComponentListRoute(val catalogType: CatalogType) : NavRoute

fun <T : Any> EntryProviderBuilder<T>.componentEntry(navController: NavController) {
    entry<CatalogTypeListRoute> {
        CatalogTypeListScreen { catalogType ->
            navController.navigate(ComponentListRoute(catalogType))
        }
    }
    entry<ComponentListRoute> { (catalogType) ->
        ComponentListScreen(catalogType)
    }
}
