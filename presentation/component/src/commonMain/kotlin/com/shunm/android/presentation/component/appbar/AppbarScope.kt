package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.RowScope

sealed interface AppbarScope

object AppbarHeadlineScope : AppbarScope

object AppbarSubtitleScope : AppbarScope

open class AppbarLeadingButtonScope : AppbarScope

data class SearchAppbarLeadingButtonScope(
    private val collapseInternal: () -> Unit,
) : AppbarLeadingButtonScope() {
    fun collapse() {
        collapseInternal()
    }
}

class AppbarTrailingElementsScope(
    rowScope: RowScope,
) : AppbarScope,
    RowScope by rowScope
