package com.shunm.android.presentation.component.graph

data class LineGraphPoint(
    val x: Float,
    val y: Float,
)

data class LineGraphContext(
    val title: String?,
    val xLabel: String?,
    val yLabel: String?,
    val points: List<LineGraphPoint>,
)

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
