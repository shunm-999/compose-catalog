package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopSearchBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.component.di.Catalogable
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClSearchAppbar(
    onSearch: (String) -> Unit = {},
    placeholder: @Composable (() -> Unit)? = null,
    leadingButton: @Composable (AppbarLeadingButtonScope.(isExpanded: Boolean) -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()

    TopSearchBar(
        state = searchBarState,
        inputField = {
            SearchBarDefaults.InputField(
                textFieldState = textFieldState,
                searchBarState = searchBarState,
                onSearch = onSearch,
                placeholder = placeholder,
                leadingIcon = if (leadingButton == null) {
                    null
                } else {
                    {
                        leadingButton(
                            AppbarLeadingButtonScope,
                            searchBarState.currentValue == SearchBarValue.Expanded,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.size(48.dp),
                        onClick = {
                            coroutineScope.launch {
                                searchBarState.animateToExpanded()
                            }
                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                        )
                    }
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
private fun ClSearchAppbarPreview() {
    ClSearchAppbar(
        onSearch = {},
        leadingButton = { isExpanded ->
            if (isExpanded) {
                PopBackIcon { }
            } else {
                MenuIcon { }
            }
        },
        placeholder = {
            Text("Search")
        },
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
