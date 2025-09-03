package com.shunm.android.presentation.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.component.di.Catalogable
import com.shunm.android.presentation.shared.ext.useIf
import com.shunm.android.presentation.shared.ext.useIfNonNull
import org.jetbrains.compose.ui.tooling.preview.Preview

@Catalogable
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
            contentScope = contentScope,
            isLeadingExists = leading != null,
            isTrailingExists = trailing != null,
        ).useIfNonNull(onClick) {
            clickable(onClick = it, enabled = enabled)
        },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = when (contentScope) {
                    is OneLineListItemContentScope, is TwoLineListItemContentScope -> Alignment.CenterVertically
                    is ThreeLineListItemContentScope -> Alignment.Top
                },
            ) {
                if (leading != null) {
                    ListItemLeadingScopeImpl(contentScope).leading()
                }

                Box(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    contentScope.Content()
                }

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
    contentScope: ListItemContentScope,
    isLeadingExists: Boolean,
    isTrailingExists: Boolean,
): Modifier = this.useIf(!isLeadingExists && !isTrailingExists) {
    when (contentScope) {
        is OneLineListItemContentScope, is ThreeLineListItemContentScope -> {
            padding(vertical = 8.dp)
        }

        is TwoLineListItemContentScope -> {
            padding(vertical = 12.dp)
        }
    }
}.useIf(!isLeadingExists) {
    padding(start = 16.dp)
}.useIf(!isTrailingExists) {
    padding(end = 16.dp)
}

@Preview
@Composable
private fun OneLineListItemPreview() {
    Column {
        ListItem(
            leading = {
                LeadingIcon(
                    imageVector = Icons.Default.Person,
                )
            },
            content = {
                oneLine {
                    Headline(text = "Headline")
                }
            },
            trailing = {
                TrailingCheckbox(
                    checked = true,
                    onCheckedChange = {},
                )
            },
        )
        ListItem(
            leading = {
                LeadingAvatar(
                    text = "A",
                )
            },
            content = {
                oneLine {
                    Headline(text = "Headline")
                }
            },
            trailing = {
                TrailingCheckbox(
                    checked = true,
                    onCheckedChange = {},
                )
            },
        )
    }
}

@Preview
@Composable
private fun TwoLineListItemPreview() {
    ListItem(
        leading = {
            LeadingIcon(
                imageVector = Icons.Default.Person,
            )
        },
        content = {
            twoLine(
                headline = {
                    Headline(text = "Headline")
                },
                supportingText = {
                    SupportingText(text = "Supporting text")
                },
            )
        },
        trailing = {
            TrailingCheckbox(
                checked = true,
                onCheckedChange = {},
            )
        },
    )
}

@Preview
@Composable
private fun ThreeLineListItemPreview() {
    ListItem(
        leading = {
            LeadingIcon(
                imageVector = Icons.Default.Person,
            )
        },
        content = {
            threeLine(
                headline = {
                    Headline(text = "Headline")
                },
                supportingText = {
                    SupportingText(text = "Supporting text Supporting text Supporting text")
                },
            )
        },
        trailing = {
            TrailingCheckbox(
                checked = true,
                onCheckedChange = {},
            )
        },
    )
}
