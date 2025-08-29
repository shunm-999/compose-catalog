package com.shunm.android.presentation.component.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shunm.android.compose_catalog.component.ComponentRes
import com.shunm.android.compose_catalog.component.arrow_back_button_content_description
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppbarLeadingButtonScope.PopBackIcon(
    onClick: () -> Unit,
) {
    LeadingIcon(
        onClick = onClick,
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        contentDescription = stringResource(
            ComponentRes.string.arrow_back_button_content_description
        )
    )
}

@Composable
fun AppbarLeadingButtonScope.LeadingIcon(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null
) {
    AppbarIcon(
        onClick = onClick,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}

@Composable
fun AppbarTrailingElementsScope.TrailingIcon(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null
) {
    AppbarIcon(
        onClick = onClick,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}

@Composable
private fun AppbarIcon(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String? = null
) {
    IconButton(
        modifier = Modifier.size(48.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
private fun PreviewPopBackIcon() {
    Surface {
        AppbarLeadingButtonScope().PopBackIcon(onClick = {})
    }
}