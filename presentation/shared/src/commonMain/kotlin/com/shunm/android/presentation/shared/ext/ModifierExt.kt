package com.shunm.android.presentation.shared.ext

import androidx.compose.ui.Modifier

fun Modifier.useIf(condition: Boolean, apply: Modifier.() -> Modifier): Modifier = if (condition) {
    this.apply()
} else {
    this
}

fun <T> Modifier.useIfNonNull(value: T?, apply: Modifier.(T) -> Modifier): Modifier = if (value != null) {
    this.apply(value)
} else {
    this
}
