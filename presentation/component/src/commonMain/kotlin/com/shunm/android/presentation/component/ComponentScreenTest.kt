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
import com.shunm.android.presentation.component.di.NullableProvider
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppbarCatalog() {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (a in AppbarLeadingButtonProvider().provide()) {
            for (b in AppbarTrailingElementsProvider().provide()) {
                for (c in AppbarHeadlineScopeProvider().provide()) {
                    for (d in NullableProvider(AppbarSubtitleScopeProvider()).provide()) {
                        ClSmallAppbar(
                            leadingButton = a,
                            trailingElements = b,
                            headline = c,
                            subtitle = d,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppbarCatalogPreview() {
    AppbarCatalog()
}
