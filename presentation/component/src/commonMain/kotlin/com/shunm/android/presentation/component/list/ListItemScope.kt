package com.shunm.android.presentation.component.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

sealed interface ListItemScope

sealed interface ListItemLeadingScope : ListItemScope

sealed interface ListItemContentScope : ListItemScope {
    @Composable
    fun Content()
}

sealed interface ListItemTrailingScope : ListItemScope

internal data class ListItemLeadingScopeImpl(
    private val contentScope: ListItemContentScope,
) : ListItemLeadingScope

data class OneLineListItemContentScope(
    val headline: @Composable () -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        headline()
    }
}

data class TwoLineListItemContentScope(
    val headline: @Composable () -> Unit,
    val supportingText: @Composable () -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column {
            headline()
            supportingText()
        }
    }
}

data class ThreeLineListItemContentScope(
    val headline: @Composable () -> Unit,
    val supportingText: @Composable () -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column {
            headline()
            supportingText()
        }
    }
}

internal data class ListItemTrailingScopeImpl(
    private val contentScope: ListItemContentScope,
) : ListItemTrailingScope

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
