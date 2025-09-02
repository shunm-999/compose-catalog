package com.shunm.android.presentation.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.component.util.Center

@Composable
fun ListItemLeadingScope.LeadingIcon(
    imageVector: ImageVector,
) {
    Center(
        modifier = Modifier.leadingPadding(),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}

context(scope: ListItemLeadingScope)
private fun Modifier.leadingPadding(): Modifier = when ((scope as ListItemLeadingScopeImpl).contentScope) {
    is OneLineListItemContentScope, is TwoLineListItemContentScope -> {
        this.padding(vertical = 8.dp, horizontal = 16.dp)
    }

    is ThreeLineListItemContentScope -> {
        this.padding(vertical = 12.dp, horizontal = 16.dp)
    }
}

context(scope: ListItemContentScope)
private fun Modifier.leadingVideoThumbnailPadding(): Modifier = this.padding(top = 12.dp, end = 16.dp, bottom = 12.dp)
