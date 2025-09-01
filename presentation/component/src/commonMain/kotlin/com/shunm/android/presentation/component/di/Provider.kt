package com.shunm.android.presentation.component.di

internal fun interface Provider<T> {
    fun provide(): List<T>
}

internal class NullableProvider<T>(
    private val original: Provider<T>? = null,
) : Provider<T?> {
    override fun provide(): List<T?> = mutableListOf<T?>().apply {
        add(null)
        if (original != null) {
            addAll(original.provide())
        }
    }
}

@CatalogProvider
internal class IntProvider : Provider<Int> {
    override fun provide(): List<Int> = listOf(0, 1)
}

@CatalogProvider
internal class FloatProvider : Provider<Float> {
    override fun provide(): List<Float> = listOf(0f, 1f)
}

@CatalogProvider
internal class DoubleProvider : Provider<Double> {
    override fun provide(): List<Double> = listOf(0.0, 1.0)
}

@CatalogProvider
internal class BooleanProvider : Provider<Boolean> {
    override fun provide(): List<Boolean> = listOf(true, false)
}

@CatalogProvider
internal class StringProvider : Provider<String> {
    override fun provide(): List<String> = listOf("", "Sample", "Sample".repeat(10))
}
