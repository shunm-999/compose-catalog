package com.shunm.android.presentation.shared.ext

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope

inline fun <T> ColumnScope.items(
    items: List<T>,
    itemContent: ColumnScope.(item: T) -> Unit,
) {
    items.forEach {
        itemContent(it)
    }
}

inline fun <T> ColumnScope.itemsIndexed(
    items: List<T>,
    itemContent: ColumnScope.(index: Int, item: T) -> Unit,
) {
    items.forEachIndexed { index, item ->
        itemContent(index, item)
    }
}

inline fun <T> RowScope.items(
    items: List<T>,
    itemContent: RowScope.(item: T) -> Unit,
) {
    items.forEach {
        itemContent(it)
    }
}

inline fun <T> RowScope.itemsIndexed(
    items: List<T>,
    itemContent: RowScope.(index: Int, item: T) -> Unit,
) {
    items.forEachIndexed { index, item ->
        itemContent(index, item)
    }
}
