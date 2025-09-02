package com.shunm.android.presentation.component.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shunm.android.presentation.component.util.Center
import com.shunm.android.presentation.component.util.TopCenter

@Composable
internal fun ListItemLeadingScope.Edge(
    content: @Composable () -> Unit,
) {
    (this as ListItemLeadingScopeImpl).contentScope.EdgeImpl(
        content = content,
    )
}

@Composable
internal fun ListItemTrailingScope.Edge(
    content: @Composable () -> Unit,
) {
    (this as ListItemTrailingScopeImpl).contentScope.EdgeImpl(
        content = content,
    )
}

@Composable
private fun ListItemContentScope.EdgeImpl(
    content: @Composable () -> Unit,
) {
    when (this) {
        is OneLineListItemContentScope, is TwoLineListItemContentScope -> {
            Center(
                modifier = Modifier.edgePadding(),
            ) {
                content()
            }
        }

        is ThreeLineListItemContentScope -> {
            TopCenter(
                modifier = Modifier.edgePadding(),
            ) {
                content()
            }
        }
    }
}
