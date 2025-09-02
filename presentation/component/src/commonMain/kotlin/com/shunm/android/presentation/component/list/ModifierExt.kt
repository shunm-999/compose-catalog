package com.shunm.android.presentation.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

context(scope: ListItemContentScope)
internal fun Modifier.edgePadding(): Modifier = when (scope) {
    is OneLineListItemContentScope, is TwoLineListItemContentScope -> {
        this.padding(vertical = 8.dp, horizontal = 16.dp)
    }

    is ThreeLineListItemContentScope -> {
        this.padding(vertical = 12.dp, horizontal = 16.dp)
    }
}
