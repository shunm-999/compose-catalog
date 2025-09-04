package com.shunm.android.presentation.component.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBarScope.NavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    enabled: Boolean = true,
) {
    ShortNavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            NavigationItemIcon(icon = icon)
        },
        label = {
            NavigationItemLabel(label = label)
        },
        iconPosition = when (itemStyle) {
            NavigationItemStyle.Vertical -> NavigationItemIconPosition.Top
            NavigationItemStyle.Horizontal -> NavigationItemIconPosition.Start
        },
        enabled = enabled,
    )
}

@Composable
private fun NavigationItemLabel(
    label: String,
) {
    Text(
        text = label,
        maxLines = 1,
        style = MaterialTheme.typography.labelMedium,
    )
}

@Composable
private fun NavigationItemIcon(
    icon: ImageVector,
) {
    Icon(
        modifier = Modifier.size(24.dp),
        imageVector = icon,
        contentDescription = null,
    )
}
