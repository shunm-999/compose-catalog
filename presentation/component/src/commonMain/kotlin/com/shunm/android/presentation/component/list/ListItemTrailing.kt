package com.shunm.android.presentation.component.list

import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ListItemTrailingScope.TrailingSupportingText(
    text: String,
) {
    Edge {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun ListItemTrailingScope.TrailingCheckbox(
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Edge {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}

@Composable
fun ListItemTrailingScope.TrailingRadioButton(
    enabled: Boolean = true,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Edge {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
        )
    }
}

@Composable
fun ListItemTrailingScope.TrailingSwitch(
    enabled: Boolean = true,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Edge {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}
