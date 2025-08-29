package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LargeFlexibleTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFlexibleTopAppBar
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClSearchAppbar() {

}

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
        }
    )
}

@Composable
fun ClMediumFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
) {
    MediumFlexibleTopAppBar(
        title = {
            Text("Small Appbar")
        }
    )
}

@Composable
fun ClLargeFlexibleAppbar(
    leadingButton: @Composable (AppbarLeadingButtonScope.() -> Unit)? = null,
    trailingElements: @Composable (AppbarTrailingElementsScope.() -> Unit)? = null,
    headline: @Composable () -> Unit,
    subtitle: @Composable (() -> Unit)? = null,
) {
    LargeFlexibleTopAppBar(
        title = {
            Text("Small Appbar")
        }
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
        }
    )
}

@Preview
@Composable
private fun ClMediumFlexibleAppbarPreview() {
    ClMediumFlexibleAppbar(
        headline = {
            Text("Small Appbar")
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
    )
}