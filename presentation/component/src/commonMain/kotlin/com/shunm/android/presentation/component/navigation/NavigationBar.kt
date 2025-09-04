package com.shunm.android.presentation.component.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.shunm.android.presentation.component.di.Catalogable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Catalogable
@Composable
fun ClFlexibleNavigationBar(
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    horizontalArrangement: Arrangement.Horizontal =
        BottomAppBarDefaults.FlexibleHorizontalArrangement,
    itemStyle: NavigationItemStyle = NavigationItemStyle.Vertical,
    items: @Composable NavigationBarScope.() -> Unit,
) {
    FlexibleBottomAppBar(
        scrollBehavior = scrollBehavior,
        horizontalArrangement = horizontalArrangement,
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
    Scaffold(
        bottomBar = {
            ClFlexibleNavigationBar(
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
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
        },
    ) {
        // do nothing
    }
}
