package com.shunm.android.presentation.component.list

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ListItemContentHeadlineScope.Headline(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ListItemContentSupportingTextScope.SupportingText(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = when (this.contentScope) {
            is OneLineListItemContentScope, is TwoLineListItemContentScope -> 1
            is ThreeLineListItemContentScope -> 2
        },
        overflow = TextOverflow.Ellipsis
    )
}
