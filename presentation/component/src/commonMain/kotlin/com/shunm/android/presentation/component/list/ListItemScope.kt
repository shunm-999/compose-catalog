package com.shunm.android.presentation.component.list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed interface ListItemScope

sealed interface ListItemLeadingScope : ListItemScope

sealed interface ListItemContentScope : ListItemScope {
    @Composable
    fun Content()
}

data object ListItemContentHeadlineScope : ListItemScope

data class ListItemContentSupportingTextScope(
    val contentScope: ListItemContentScope,
) : ListItemScope

sealed interface ListItemTrailingScope : ListItemScope

internal data class ListItemLeadingScopeImpl(
    val contentScope: ListItemContentScope,
) : ListItemLeadingScope

data class OneLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.contentPadding()
        ) {
            ListItemContentHeadlineScope.headline()
        }
    }
}

data class TwoLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    val supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.contentPadding()
        ) {
            ListItemContentHeadlineScope.headline()
            ListItemContentSupportingTextScope(this@TwoLineListItemContentScope).supportingText()
        }
    }
}

data class ThreeLineListItemContentScope(
    val headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    val supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
) : ListItemContentScope {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.contentPadding()
        ) {
            ListItemContentHeadlineScope.headline()
            ListItemContentSupportingTextScope(this@ThreeLineListItemContentScope).supportingText()
        }
    }
}

internal data class ListItemTrailingScopeImpl(
    val contentScope: ListItemContentScope,
) : ListItemTrailingScope

data object ListItemContentScopeProvider {
    fun oneLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
    ): ListItemContentScope = OneLineListItemContentScope(
        headline = headline,
    )

    fun twoLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
        supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
    ): ListItemContentScope = TwoLineListItemContentScope(
        headline = headline,
        supportingText = supportingText,
    )

    fun threeLine(
        headline: @Composable ListItemContentHeadlineScope.() -> Unit,
        supportingText: @Composable ListItemContentSupportingTextScope.() -> Unit,
    ): ListItemContentScope = ThreeLineListItemContentScope(
        headline = headline,
        supportingText = supportingText,
    )
}
