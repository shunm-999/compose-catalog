package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.di.Cataloglable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClSearchAppbar() {
}

@Cataloglable
@Composable
fun ClSmallAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            if (subtitle != null) {
                Column {
                    ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                        headline()
                    }
                    ProvideTextStyle(MaterialTheme.typography.bodySmall) {
                        subtitle()
                    }
                }
            } else {
                ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                    headline()
                }
            }
        },
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope().leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
        },
    )
}

@Cataloglable
@Composable
fun ClMediumFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
) {
    MediumFlexibleTopAppBar(
        title = headline,
        subtitle = subtitle,
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope().leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
        },
    )
}

@Cataloglable
@Composable
fun ClLargeFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
) {
    LargeFlexibleTopAppBar(
        title = headline,
        subtitle = subtitle,
        navigationIcon = {
            if (leadingButton != null) {
                AppbarLeadingButtonScope().leadingButton()
            }
        },
        actions = {
            if (trailingElements != null) {
                AppbarTrailingElementsScope(this).trailingElements()
            }
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
    )
}
