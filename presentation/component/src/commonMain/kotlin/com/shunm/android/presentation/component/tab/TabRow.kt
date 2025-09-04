package com.shunm.android.presentation.component.tab

import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun <T> ClTabRow(
    selected: T,
    isPrimary: Boolean = true,
    isScrollable: Boolean = true,
    tabs: TabRowScope.() -> TabRowContext<T>,
) {
    val rememberedTabs by rememberUpdatedState(tabs)
    val context = remember(rememberedTabs) {
        TabRowScope.rememberedTabs()
    }
    val tabRowType = remember(
        isPrimary,
        isScrollable,
    ) {
        getTabRowType(
            isPrimary = isPrimary,
            isScrollable = isScrollable,
        )
    }

    when (tabRowType) {
        TabRowType.Primary -> {
            PrimaryTabRow(
                selectedTabIndex = context.items.indexOf(selected),
                tabs = {
                    context.items.forEach { item ->
                        context.itemContent(TabItemScope(isSelected = item == selected), item)
                    }
                },
            )
        }

        TabRowType.PrimaryScrollable -> {
            PrimaryScrollableTabRow(
                selectedTabIndex = context.items.indexOf(selected),
                tabs = {
                    context.items.forEach { item ->
                        context.itemContent(TabItemScope(isSelected = item == selected), item)
                    }
                },
            )
        }

        TabRowType.Secondary -> {
            SecondaryTabRow(
                selectedTabIndex = context.items.indexOf(selected),
                tabs = {
                    context.items.forEach { item ->
                        context.itemContent(TabItemScope(isSelected = item == selected), item)
                    }
                },
            )
        }

        TabRowType.SecondaryScrollable -> {
            SecondaryScrollableTabRow(
                selectedTabIndex = context.items.indexOf(selected),
                tabs = {
                    context.items.forEach { item ->
                        context.itemContent(TabItemScope(isSelected = item == selected), item)
                    }
                },
            )
        }
    }
}

private sealed interface TabRowType {
    object Primary : TabRowType
    object PrimaryScrollable : TabRowType
    object Secondary : TabRowType
    object SecondaryScrollable : TabRowType
}

private fun getTabRowType(
    isPrimary: Boolean,
    isScrollable: Boolean,
): TabRowType {
    return when {
        isPrimary && isScrollable -> TabRowType.PrimaryScrollable
        isPrimary && !isScrollable -> TabRowType.Primary
        !isPrimary && isScrollable -> TabRowType.SecondaryScrollable
        else -> TabRowType.Secondary
    }
}

@Preview
@Composable
private fun ClTabRowPreview() {
    ClTabRow(
        isPrimary = true,
        selected = 0,
        tabs = {
            items(listOf(0, 1, 2)) { item ->
                TabItem(
                    selected = isSelected,
                    onClick = {},
                    text = "Tab $item",
                )
            }
        },
    )
}
