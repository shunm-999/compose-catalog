package com.shunm.android.presentation.shared.ext

import androidx.compose.ui.Modifier

fun Modifier.useIf(condition: Boolean, apply: Modifier.() -> Modifier): Modifier = if (condition) {
    this.apply()
} else {
    this
}
