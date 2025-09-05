package com.shunm.android.presentation.component.graph

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shunm.android.presentation.component.di.Catalogable
import com.shunm.android.presentation.component.tab.ClTabRow
import com.shunm.android.presentation.component.tab.TabItem
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.truncate

data class LineGraphPoint(
    val x: Float,
    val y: Float,
)

data class LineGraphContext(
    val title: String?,
    val xLabel: String?,
    val yLabel: String?,
    val points: List<LineGraphPoint>,
    val scaleCount: Int,
) {

    val xRange: ClosedFloatingPointRange<Float>
        get() = points.minOf { it.x }.let { minX ->
            points.maxOf { it.x }.let { maxX ->
                floor(minX)..ceil(maxX)
            }
        }

    val yRange: ClosedFloatingPointRange<Float>
        get() = points.minOf { it.y }.let { minY ->
            points.maxOf { it.y }.let { maxY ->
                floor(minY)..ceil(maxY)
            }
        }

    fun xScale(index: Int): Float {
        val minX = xRange.start
        val maxX = xRange.endInclusive

        if (scaleCount <= 1) {
            return minX
        }
        return truncate(minX + ((maxX - minX) / (scaleCount - 1) * index))
    }

    fun yScale(index: Int): Float {
        val minY = yRange.start
        val maxY = yRange.endInclusive

        if (scaleCount <= 1) {
            return minY
        }
        return truncate(minY + ((maxY - minY) / (scaleCount - 1) * index))
    }
}

class LineGraphContextBuilder {
    var title: String? = null
    var xLabel: String? = null
    var yLabel: String? = null
    val points: MutableList<LineGraphPoint> = mutableListOf()

    var scaleCount: Int = 10

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
        scaleCount = scaleCount,
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
fun LineGraph(
    context: LineGraphContext,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    lineColor: Color = MaterialTheme.colorScheme.primary,
    lineWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.onSurface,
    borderWidth: Dp = 1.dp,
    yScaleSpace: Dp = 32.dp,
) {
    with(context) {
        LineGraphOuter(
            containerColor = containerColor,
            contentColor = contentColor,
            borderColor = borderColor,
            borderWidth = borderWidth,
            yScaleSpace = yScaleSpace,
        ) {
            LineGraphInner(
                lineColor = lineColor,
                lineWidth = lineWidth,
            )
        }
    }
}

@Composable
context(context: LineGraphContext)
private fun LineGraphOuter(
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    borderWidth: Dp,
    yScaleSpace: Dp,
    inner: @Composable () -> Unit,
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
    ) {
        Row {
            Column {
                TitleSpace()
                YAxis(
                    borderColor = borderColor,
                    borderWidth = borderWidth,
                    yScaleSpace = yScaleSpace,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Title()
                LineGraphBox(
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                    yScaleSpace = yScaleSpace,
                    inner = inner,
                )
                XAxis(
                    borderColor = borderColor,
                    borderWidth = borderWidth,
                )
            }
        }
    }
}

@Composable
context(context: LineGraphContext)
private fun LineGraphBox(
    borderWidth: Dp,
    borderColor: Color,
    yScaleSpace: Dp,
    inner: @Composable (() -> Unit),
) {
    Box(
        Modifier.border(
            width = borderWidth,
            color = borderColor,
        ).padding(
            vertical = 8.dp,
            horizontal = 16.dp,
        ).fillMaxWidth().height(yScaleSpace * (context.scaleCount - 1)),
    ) {
        inner()
    }
}

@Composable
context(context: LineGraphContext)
private fun LineGraphInner(
    lineColor: Color,
    lineWidth: Dp,
) {
    Box(
        Modifier.fillMaxSize()
            .drawBehind {
                fun getX(point: LineGraphPoint): Float {
                    val xPercent = (point.x - context.xRange.start) / (context.xRange.endInclusive - context.xRange.start)
                    return 16.dp.toPx() + (xPercent * (size.width - (16.dp.toPx() * 2)))
                }

                fun getY(point: LineGraphPoint): Float {
                    val yPercent = (point.y - context.yRange.start) / (context.yRange.endInclusive - context.yRange.start)
                    return size.height - (8.dp.toPx() + (yPercent * (size.height - (8.dp.toPx() * 2))))
                }

                val line = Path().apply {
                    context.points.forEachIndexed { index, point ->
                        val x = getX(point)
                        val y = getY(point)
                        if (index == 0) {
                            moveTo(x, y)
                        } else {
                            lineTo(x, y)
                        }
                    }
                }
                drawPath(
                    path = line,
                    color = lineColor,
                    style = Stroke(
                        width = lineWidth.toPx(),
                    ),
                )

                val startPoint = context.points.first()
                val endPoint = context.points.last()
                val highestPoint = context.points.maxBy { it.y }

                val gradientArea = line.apply {
                    val startX = getX(startPoint)
                    val startY = getY(startPoint)
                    val endX = getX(endPoint)

                    lineTo(endX, size.height)
                    lineTo(startX, size.height)
                    lineTo(startX, startY)
                }
                drawPath(
                    path = gradientArea,
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            lineColor.copy(alpha = 0.8f),
                            lineColor.copy(alpha = 0.5f),
                            lineColor.copy(alpha = 0.01f),
                        ),
                        startY = getY(highestPoint),
                        endY = size.height,
                    ),
                )
            },
    ) {
    }
}

@Composable
context(context: LineGraphContext)
private fun ColumnScope.Title() {
    if (context.title != null) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(vertical = 4.dp),
            text = context.title,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
context(context: LineGraphContext)
private fun ColumnScope.TitleSpace() {
    if (context.title != null) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(vertical = 4.dp).alpha(0f),
            text = "a",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
context(context: LineGraphContext)
private fun XAxis(
    borderColor: Color,
    borderWidth: Dp,
) {
    val textMeasurer = rememberTextMeasurer()
    val textStyle = MaterialTheme.typography.labelSmall
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
                    val text = context.xScale(count).toString()
                    val textLayoutResult = textMeasurer.measure(
                        text = text,
                        style = textStyle,
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(x - (textLayoutResult.size.width / 2), 8.dp.toPx()),
                    )
                }
            },
        )
        if (context.xLabel != null) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(vertical = 4.dp),
                text = context.xLabel,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
context(context: LineGraphContext)
private fun YAxis(
    borderColor: Color,
    borderWidth: Dp,
    yScaleSpace: Dp,
) {
    val textMeasurer = rememberTextMeasurer()
    val textStyle = MaterialTheme.typography.labelSmall
    Row(
        modifier = Modifier,
    ) {
        if (context.yLabel != null) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp),
                text = context.yLabel.toCharArray().joinToString(
                    separator = "\n",
                ),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
            )
        }
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(yScaleSpace * context.scaleCount)
                .drawBehind {
                    repeat(context.scaleCount) { count ->
                        val y = 8.dp.toPx() + (yScaleSpace * count).toPx()
                        drawLine(
                            color = borderColor,
                            start = Offset(size.width - 4.dp.toPx(), y),
                            end = Offset(size.width, y),
                            strokeWidth = borderWidth.toPx(),
                        )
                        val text = context.yScale(count).toString()
                        val textLayoutResult = textMeasurer.measure(
                            text = text,
                            style = textStyle,
                        )
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                size.width - 8.dp.toPx() - textLayoutResult.size.width,
                                y - (textLayoutResult.size.height / 2),
                            ),
                        )
                    }
                },
        )
    }
}

private enum class LineGraphTab {
    Daily,
    Weekly,
    Monthly,
}

@Catalogable
@Composable
fun LineGraphCatalogable() {
    var selectedTab by remember { mutableStateOf(LineGraphTab.Daily) }

    // ① タブごとのポイントを作る関数
    fun generatePoints(tab: LineGraphTab): Pair<List<Float>, List<Float>> = when (tab) {
        LineGraphTab.Daily -> {
            // 0..23時のサイン波（見やすいようにスケール）
            val x = (0..23).map { it.toFloat() }
            val y = x.map { t -> (kotlin.math.sin(t / 24f * (2f * PI).toFloat()) * 40f + 60f) }
            x to y
        }
        LineGraphTab.Weekly -> {
            // 月〜日の7点（好きに差し替え可）
            val x = (1..7).map { it.toFloat() }
            val y = listOf(12f, 18f, 9f, 14f, 20f, 17f, 11f)
            x to y
        }
        LineGraphTab.Monthly -> {
            // 1..30日の緩やかなトレンド＋周期変動
            val x = (1..30).map { it.toFloat() }
            val y = x.map { t ->
                val trend = 0.8f * t // 緩やかな上昇
                val wave = 6f * kotlin.math.sin(t / 5f) // 軽い周期成分
                trend + wave + 10f
            }
            x to y
        }
    }

    // ② タブ変更時だけポイントを再計算
    val points = remember(selectedTab) { generatePoints(selectedTab) }

    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ClTabRow(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                isPrimary = true,
                selected = selectedTab,
                tabs = {
                    items(LineGraphTab.entries) { item ->
                        TabItem(
                            selected = item == selectedTab, // ← 修正
                            onClick = { selectedTab = item },
                            text = item.name,
                        )
                    }
                },
            )

            // ③ ポイントを使ってグラフ用コンテキストを生成
            val context = rememberLineGraphContext {
                title = when (selectedTab) {
                    LineGraphTab.Daily -> "Daily Line"
                    LineGraphTab.Weekly -> "Weekly Line"
                    LineGraphTab.Monthly -> "Monthly Line"
                }
                xLabel = when (selectedTab) {
                    LineGraphTab.Daily -> "Hour"
                    LineGraphTab.Weekly -> "Day of Week"
                    LineGraphTab.Monthly -> "Day"
                }
                yLabel = "Value"

                plot(
                    x = points.first,
                    y = points.second,
                )
            }

            LineGraph(context = context)
        }
    }
}

@Preview
@Composable
private fun LineGraphPreview() {
    val context = rememberLineGraphContext {
        title = "Sample Line Graph"
        xLabel = "X Axis"
        yLabel = "Y Axis"
        plot(
            x = listOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f),
            y = listOf(0f, 1f, 64f, 4f, 100f, 16f, 25f, 9f, 36f, 49f, 81f),
        )
    }
    LineGraph(
        context = context,
    )
}
