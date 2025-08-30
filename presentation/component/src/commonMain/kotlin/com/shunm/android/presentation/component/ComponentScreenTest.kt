package com.shunm.android.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.component.appbar.AppbarHeadlineScopeProvider
import com.shunm.android.presentation.component.appbar.AppbarLeadingButtonProvider
import com.shunm.android.presentation.component.appbar.AppbarSubtitleScopeProvider
import com.shunm.android.presentation.component.appbar.AppbarTrailingElementsProvider
import com.shunm.android.presentation.component.appbar.ClSmallAppbar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppbarCatalog() {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ClSmallAppbar(
            leadingButton = AppbarLeadingButtonProvider().provide()[0],
            trailingElements = AppbarTrailingElementsProvider().provide()[0],
            headline = AppbarHeadlineScopeProvider().provide()[0],
            subtitle = AppbarSubtitleScopeProvider().provide()[0]
        )
        ClSmallAppbar(
            leadingButton = AppbarLeadingButtonProvider().provide()[0],
            trailingElements = AppbarTrailingElementsProvider().provide()[0],
            headline = AppbarHeadlineScopeProvider().provide()[0],
            subtitle = AppbarSubtitleScopeProvider().provide()[0]
        )
    }
}

@Preview
@Composable
fun AppbarCatalogPreview() {
    AppbarCatalog()
}
