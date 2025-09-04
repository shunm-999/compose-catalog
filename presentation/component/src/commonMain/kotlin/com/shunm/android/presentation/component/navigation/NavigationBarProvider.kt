package com.shunm.android.presentation.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.di.CatalogProvider
import com.shunm.android.presentation.component.di.Provider

@CatalogProvider
internal class NavigationItemStyleProvider : Provider<NavigationItemStyle> {
    override fun provide(): List<NavigationItemStyle> {
        return NavigationItemStyle.entries
    }
}

@CatalogProvider
internal class NavigationItemsCountProvider : Provider<@Composable NavigationBarScope.() -> Unit> {
    override fun provide(): List<@Composable NavigationBarScope.() -> Unit> {
        val items = listOf<@Composable NavigationBarScope.() -> Unit>(
            {
                NavigationItem(
                    selected = true,
                    onClick = {},
                    icon = Icons.Default.Home,
                    label = "Home",
                )
            },
            {
                NavigationItem(
                    selected = false,
                    onClick = {},
                    icon = Icons.Default.Favorite,
                    label = "Favorite",
                )
            },
            {
                NavigationItem(
                    selected = false,
                    onClick = {},
                    icon = Icons.Default.Person,
                    label = "Profile",
                )
            },
            {
                NavigationItem(
                    selected = false,
                    onClick = {},
                    icon = Icons.Default.Search,
                    label = "Search",
                )
            },
            {
                NavigationItem(
                    selected = false,
                    onClick = {},
                    icon = Icons.Default.Settings,
                    label = "Settings",
                )
            },
        )
        return (3..5).map {
            {
                items.take(it).forEach { item ->
                    item()
                }
            }
        }
    }
}
