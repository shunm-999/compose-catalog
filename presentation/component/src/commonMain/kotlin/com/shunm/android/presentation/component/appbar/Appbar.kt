package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopSearchBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.di.Catalogable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Catalogable
@Composable
fun ClSearchAppbar(
    onSearch: (String) -> Unit = {},
) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    TopSearchBar(
        state = searchBarState,
        inputField = {
            SearchBarDefaults.InputField(
                textFieldState = textFieldState,
                searchBarState = searchBarState,
                onSearch = {
                },
            )
        },
    )
}

@Catalogable
@Composable
fun ClSmallAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable AppbarHeadlineScope.() -> Unit,
    subtitle: @Composable (AppbarSubtitleScope.() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = {
            if (subtitle != null) {
                Column {
                    ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                        AppbarHeadlineScope.headline()
                    }
                    ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                        AppbarSubtitleScope.subtitle()
                    }
                }
            } else {
                ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                    AppbarHeadlineScope.headline()
                }
            }
        },
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope.leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Catalogable
@Composable
fun ClMediumFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable AppbarHeadlineScope.() -> Unit,
    subtitle: @Composable (AppbarSubtitleScope.() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    MediumFlexibleTopAppBar(
        title = {
            AppbarHeadlineScope.headline()
        },
        subtitle = subtitle?.let {
            {
                AppbarSubtitleScope.subtitle()
            }
        },
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope.leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Catalogable
@Composable
fun ClLargeFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable AppbarHeadlineScope.() -> Unit,
    subtitle: @Composable (AppbarSubtitleScope.() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    LargeFlexibleTopAppBar(
        title = {
            AppbarHeadlineScope.headline()
        },
        subtitle = subtitle?.let {
            {
                AppbarSubtitleScope.subtitle()
            }
        },
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope.leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview
@Composable
private fun ClSmallAppbarPreview() {
    ClSmallAppbar(
        headline = {
            Text("Small Appbar")
        },
        subtitle = {
            Text("Subtitle")
        },
        leadingButton = {
            PopBackIcon { }
        },
        trailingElements = {
            SearchIcon { }
            MoreVertIcon { }
        },
    )
}

@Preview
@Composable
private fun ClMediumFlexibleAppbarPreview() {
    ClMediumFlexibleAppbar(
        headline = {
            Text("Small Appbar")
        },
        subtitle = {
            Text("Subtitle")
        },
        leadingButton = {
            PopBackIcon { }
        },
        trailingElements = {
            SearchIcon { }
            MoreVertIcon { }
        },
    )
}

@Preview
@Composable
private fun ClLargeFlexibleAppbarPreview() {
    ClLargeFlexibleAppbar(
        headline = {
            Text("Small Appbar")
        },
        subtitle = {
            Text("Subtitle")
        },
        leadingButton = {
            PopBackIcon { }
        },
        trailingElements = {
            SearchIcon { }
            MoreVertIcon { }
        },
    )
}
