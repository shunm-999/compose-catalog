package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.RowScope

sealed interface AppbarScope

object AppbarHeadlineScope : AppbarScope

object AppbarSubtitleScope : AppbarScope

object AppbarLeadingButtonScope : AppbarScope

class AppbarTrailingElementsScope(
    rowScope: RowScope,
) : AppbarScope,
    RowScope by rowScope
