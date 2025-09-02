package com.shunm.android.presentation.component.list

import androidx.compose.runtime.Composable

class ListItem

@Composable
fun ListItem(
    leading: @Composable (ListItemLeadingScope.() -> Unit)? = null,
    content: @Composable ListItemContentScope.() -> Unit,
    trailing: @Composable (ListItemTrailingScope.() -> Unit)? = null,
) {
}
