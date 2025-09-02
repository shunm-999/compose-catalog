package com.shunm.android.presentation.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.shared.ext.useIf
import com.shunm.android.presentation.shared.ext.useIfNonNull

@Composable
fun ListItem(
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    hasDivider: Boolean = false,
    leading: @Composable (ListItemLeadingScope.() -> Unit)? = null,
    content: ListItemContentScopeProvider.() -> ListItemContentScope,
    trailing: @Composable (ListItemTrailingScope.() -> Unit)? = null,
) {
    val rememberContentProvider by rememberUpdatedState(content)
    val contentScope = ListItemContentScopeProvider.rememberContentProvider()

    Surface(
        modifier = Modifier.containerPadding(
            isLeadingExists = leading != null,
            isTrailingExists = trailing != null,
        ).useIfNonNull(onClick) {
            clickable(onClick = it, enabled = enabled)
        },
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (leading != null) {
                    ListItemLeadingScopeImpl(contentScope).leading()
                }

                contentScope.Content()

                if (trailing != null) {
                    ListItemTrailingScopeImpl(contentScope).trailing()
                }
            }
            if (hasDivider) {
                HorizontalDivider()
            }
        }
    }
}

private fun Modifier.containerPadding(
    isLeadingExists: Boolean,
    isTrailingExists: Boolean,
): Modifier = this.useIf(!isLeadingExists && !isTrailingExists) {
    padding(vertical = 16.dp)
}.useIf(!isLeadingExists) {
    padding(start = 16.dp)
}.useIf(!isTrailingExists) {
    padding(end = 16.dp)
}
