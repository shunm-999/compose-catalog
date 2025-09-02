package com.shunm.android.presentation.component.list

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListItemContentHeadlineScope.Headline(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun ListItemContentSupportingTextScope.SupportingText(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
    )
}
