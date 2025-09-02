package com.shunm.android.presentation.component.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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

@Composable
fun ListItemLeadingScope.LeadingAvatar(
    imageVector: ImageVector,
    contentDescription: String? = null,
) {
    Center(
        modifier = Modifier.leadingPadding(),
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun ListItemLeadingScope.LeadingImage(
    painter: Painter,
    contentDescription: String? = null,
) {
    Center(
        modifier = Modifier.leadingPadding(),
    ) {
        Image(
            modifier = Modifier.size(56.dp),
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun ListItemLeadingScope.LeadingImage(
    url: String,
    contentDescription: String? = null,
) {
    Center(
        modifier = Modifier.leadingPadding(),
    ) {
        AsyncImage(
            modifier = Modifier.size(56.dp),
            model = url,
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun ListItemLeadingScope.LeadingVideoThumbnail(
    painter: Painter,
    contentDescription: String? = null,
) {
    Center(
        modifier = Modifier.leadingVideoThumbnailPadding(),
    ) {
        Image(
            modifier = Modifier.height(64.dp),
            painter = painter,
            contentDescription = contentDescription,
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

context(scope: ListItemLeadingScope)
private fun Modifier.leadingVideoThumbnailPadding(): Modifier = this.padding(top = 12.dp, end = 16.dp, bottom = 12.dp)
