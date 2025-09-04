package com.shunm.android.presentation.component.navigation

import androidx.compose.foundation.layout.RowScope

enum class NavigationItemStyle {
    Vertical,
    Horizontal,
}

data class NavigationBarScope(
    private val rowScope: RowScope,
    val itemStyle: NavigationItemStyle,
) : RowScope by rowScope
