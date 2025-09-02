package com.shunm.android.presentation.component.list

import androidx.compose.runtime.Composable

sealed interface ListItemScope

data object ListItemLeadingScope : ListItemScope

sealed interface ListItemContentScope : ListItemScope

data object ListItemTrailingScope : ListItemScope

data class OneLineListItemContentScope(
    val headline: @Composable () -> Unit,
) : ListItemContentScope

data class TwoLineListItemContentScope(
    val headline: @Composable () -> Unit,
    val supportingText: @Composable () -> Unit,
) : ListItemContentScope

data class ThreeLineListItemContentScope(
    val headline: @Composable () -> Unit,
    val supportingText: @Composable () -> Unit,
) : ListItemContentScope

data object ListItemContentScopeProvider {
    fun oneLine(
        headline: @Composable () -> Unit,
    ): ListItemContentScope = OneLineListItemContentScope(headline)

    fun twoLine(
        headline: @Composable () -> Unit,
        supportingText: @Composable () -> Unit,
    ): ListItemContentScope = TwoLineListItemContentScope(headline, supportingText)

    fun threeLine(
        headline: @Composable () -> Unit,
        supportingText: @Composable () -> Unit,
    ): ListItemContentScope = ThreeLineListItemContentScope(headline, supportingText)
}
