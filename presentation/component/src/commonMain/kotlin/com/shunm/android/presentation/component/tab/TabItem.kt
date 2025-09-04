package com.shunm.android.presentation.component.tab

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TabItem(
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    imageVector: ImageVector? = null,
    text: String? = null,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        icon = if (imageVector != null) {
            {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = imageVector,
                    contentDescription = null,
                )
            }
        } else {
            null
        },
        text = if (text != null) {
            {
                Text(text = text)
            }
        } else {
            null
        },
    )
}
