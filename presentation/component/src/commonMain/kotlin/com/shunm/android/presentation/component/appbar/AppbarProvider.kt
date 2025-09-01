package com.shunm.android.presentation.component.appbar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.di.CatalogProvider
import com.shunm.android.presentation.component.di.Provider

@CatalogProvider
internal class AppbarHeadlineScopeProvider : Provider<@Composable AppbarHeadlineScope.() -> Unit> {
    override fun provide(): List<@Composable AppbarHeadlineScope.() -> Unit> {
        return listOf(
            {
                Text(text = "Headline")
            },
        )
    }
}

@CatalogProvider
internal class AppbarSubtitleScopeProvider : Provider<@Composable (AppbarSubtitleScope.() -> Unit)> {
    override fun provide(): List<@Composable (AppbarSubtitleScope.() -> Unit)> {
        return listOf(
            {
                Text(text = "Subtitle")
            },
        )
    }
}

@CatalogProvider
internal class AppbarLeadingButtonProvider : Provider<@Composable (AppbarLeadingButtonScope.() -> Unit)> {
    override fun provide(): List<@Composable (AppbarLeadingButtonScope.() -> Unit)> {
        return listOf(
            {
                PopBackIcon {}
            },
        )
    }
}

@CatalogProvider
internal class AppbarTrailingElementsProvider : Provider<@Composable (AppbarTrailingElementsScope.() -> Unit)> {
    override fun provide(): List<@Composable (AppbarTrailingElementsScope.() -> Unit)> {
        return listOf(
            {
                SearchIcon { }
            },
            {
                MoreVertIcon { }
            },
            {
                SearchIcon { }
                MoreVertIcon { }
            },
        )
    }
}
