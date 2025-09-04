package com.shunm.android.presentation.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClFlexibleNavigationBar(
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    itemStyle: NavigationItemStyle = NavigationItemStyle.Vertical,
    items: @Composable NavigationBarScope.() -> Unit,
) {
    FlexibleBottomAppBar(
        scrollBehavior = scrollBehavior,
    ) {
        val scope = remember(this, itemStyle) {
            NavigationBarScope(
                rowScope = this,
                itemStyle = itemStyle,
            )
        }
        scope.items()
    }
}

@Preview
@Composable
private fun ClFlexibleNavigationBarPreview() {
    ClFlexibleNavigationBar {
        NavigationItem(
            selected = true,
            onClick = {},
            icon = Icons.Default.Home,
            label = "Home",
        )
        NavigationItem(
            selected = false,
            onClick = {},
            icon = Icons.Default.Favorite,
            label = "Favorite",
        )
        NavigationItem(
            selected = false,
            onClick = {},
            icon = Icons.Default.Person,
            label = "Profile",
        )
    }
}
