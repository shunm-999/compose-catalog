package com.shunm.android.presentation.component.tab

import androidx.compose.runtime.Composable

data object TabRowScope {
    fun <T> items(items: List<T>, itemContent: @Composable TabItemScope<T>.(item: T) -> Unit): TabRowContext<T> {
        return TabRowContext(
            items = items,
            itemContent = itemContent,
        )
    }
}

data class TabRowContext<T>(
    val items: List<T>,
    val itemContent: @Composable TabItemScope<T>.(item: T) -> Unit,
)

data class TabItemScope<T>(
    val isSelected: Boolean,
)
