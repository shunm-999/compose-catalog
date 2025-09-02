package com.shunm.android.presentation.component.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

sealed interface ListItemScope

sealed interface ListItemLeadingScope : ListItemScope

sealed interface ListItemContentScope : ListItemScope {
    @Composable
    fun Content()
}

data object ListItemContentHeadlineScope : ListItemScope

data object ListItemContentSupportingTextScope : ListItemScope

sealed interface ListItemTrailingScope : ListItemScope

internal data class ListItemLeadingScopeImpl(
    private val contentScope: ListItemContentScope,
) : ListItemLeadingScope

data class OneLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        ListItemContentHeadlineScope.headline()
    }
}

data class TwoLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    val supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column {
            ListItemContentHeadlineScope.headline()
            ListItemContentSupportingTextScope.supportingText()
        }
    }
}

data class ThreeLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    val supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column {
            ListItemContentHeadlineScope.headline()
            ListItemContentSupportingTextScope.supportingText()
        }
    }
}

internal data class ListItemTrailingScopeImpl(
    private val contentScope: ListItemContentScope,
) : ListItemTrailingScope

data object ListItemContentScopeProvider {
    fun oneLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    ): ListItemContentScope = OneLineListItemContentScope(headline)

    fun twoLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
        supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
    ): ListItemContentScope = TwoLineListItemContentScope(headline, supportingText)

    fun threeLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
        supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
    ): ListItemContentScope = ThreeLineListItemContentScope(headline, supportingText)
}
