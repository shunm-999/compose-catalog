package com.shunm.android.presentation.component.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListItemTrailingScope.SupportingText(
    text: String,
) {
    Edge {
        Text(
            text = text,
        )
    }
}

@Composable
fun ListItemTrailingScope.Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Edge {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
fun ListItemTrailingScope.RadioButton(
    selected: Boolean,
    onClick: () -> Unit,
) {
    Edge {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
    }
}

@Composable
fun ListItemTrailingScope.Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Edge {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}
