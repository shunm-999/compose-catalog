package com.shunm.android.presentation.component.graph

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.ceil
import kotlin.math.floor

data class LineGraphPoint(
    val x: Float,
    val y: Float,
)

data class LineGraphContext(
    val title: String?,
    val xLabel: String?,
    val yLabel: String?,
    val points: List<LineGraphPoint>,
) {
    val scaleCount
        get() = points.size

    val xRange: ClosedFloatingPointRange<Float>
        get() = points.minOf { it.x }.let { minX ->
            points.minOf { it.x }.let { maxX ->
                floor(minX)..ceil(maxX)
            }
        }

    val yRange: ClosedFloatingPointRange<Float>
        get() = points.minOf { it.y }.let { minY ->
            points.minOf { it.y }.let { maxY ->
                floor(minY)..ceil(maxY)
            }
        }
}

class LineGraphContextBuilder {
    var title: String? = null
    var xLabel: String? = null
    var yLabel: String? = null
    val points: MutableList<LineGraphPoint> = mutableListOf()

    fun plot(x: Float, y: Float) {
        points.add(LineGraphPoint(x, y))
    }

    fun plot(x: List<Float>, y: List<Float>) {
        val size = minOf(x.size, y.size)
        for (i in 0 until size) {
            points.add(LineGraphPoint(x[i], y[i]))
        }
    }

    fun build(): LineGraphContext = LineGraphContext(
        title = title,
        xLabel = xLabel,
        yLabel = yLabel,
        points = points.toList(),
    )
}

@Composable
fun rememberLineGraphContext(
    block: LineGraphContextBuilder.() -> Unit,
): LineGraphContext {
    val rememberedBlock by rememberUpdatedState(block)
    return remember(rememberedBlock) {
        LineGraphContextBuilder().apply {
            rememberedBlock()
        }.build()
    }
}

@Composable
fun ListGraph(
    context: LineGraphContext,
    scaleCount: Int = 10,
    yScaleSpace: Dp = 32.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    lineColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.onSurface,
    borderWidth: Dp = 1.dp,
) {
    with(context) {
        ListGraphOuter(
            modifier = Modifier.fillMaxWidth().height(yScaleSpace * scaleCount + 16.dp),
            containerColor = containerColor,
            contentColor = contentColor,
            borderColor = borderColor,
            borderWidth = borderWidth,
        ) {
        }
    }
}

@Composable
context(context: LineGraphContext)
private fun ListGraphOuter(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    borderWidth: Dp,
    inner: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = containerColor,
        contentColor = contentColor,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Box(
                    Modifier.border(
                        width = borderWidth,
                        color = borderColor,
                    ).padding(
                        vertical = 8.dp,
                        horizontal = 16.dp,
                    ).fillMaxWidth().weight(1f),
                ) {
                    inner()
                }
                LineGraphXLabel(
                    borderColor = borderColor,
                    borderWidth = borderWidth,
                )
            }
        }
    }
}

@Composable
context(context: LineGraphContext)
fun LineGraphXLabel(
    borderColor: Color,
    borderWidth: Dp,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(24.dp).drawBehind {
                val xScale = (size.width - (16.dp.toPx() * 2)) / (context.scaleCount - 1)
                repeat(context.scaleCount) { count ->
                    val x = 16.dp.toPx() + (xScale * count)
                    drawLine(
                        color = borderColor,
                        start = Offset(x, 0f),
                        end = Offset(x, 4.dp.toPx()),
                        strokeWidth = borderWidth.toPx(),
                    )
                }
            },
        )
    }
}

private sealed interface OrientationScope {
    object Horizontal : OrientationScope
    object Vertical : OrientationScope
}

@Preview
@Composable
private fun ListGraphPreview() {
    val context = rememberLineGraphContext {
        title = "Sample Line Graph"
        xLabel = "X Axis"
        yLabel = "Y Axis"
        plot(
            x = listOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f),
            y = listOf(0f, 1f, 4f, 9f, 16f, 25f, 36f, 49f, 64f, 81f, 100f),
        )
    }
    ListGraph(
        context = context,
    )
}
