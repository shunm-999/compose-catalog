package com.shunm.android.presentation.component.di

import androidx.compose.runtime.Composable
import com.shunm.android.presentation.component.appbar.AppbarLeadingButtonScope
import com.shunm.android.presentation.component.appbar.AppbarTrailingElementsScope
import com.shunm.android.presentation.component.appbar.MoreVertIcon
import com.shunm.android.presentation.component.appbar.PopBackIcon
import com.shunm.android.presentation.component.appbar.SearchIcon

internal fun interface Provider<T> {
    fun provide(): List<T>
}

internal class NullableProvider<T>(
    private val original: Provider<T>,
) : Provider<T?> {
    override fun provide(): List<T?> = mutableListOf<T?>().apply {
        add(null)
        addAll(original.provide())
    }
}


// DefaultProviders
internal object DefaultProviders {
    fun providers(): List<Provider<*>> = listOf(
        IntProvider(),
        FloatProvider(),
        DoubleProvider(),
        BooleanProvider(),
        StringProvider(),
    )
}

internal class IntProvider : Provider<Int> {
    override fun provide(): List<Int> = listOf(0, 1)
}

internal class FloatProvider : Provider<Float> {
    override fun provide(): List<Float> = listOf(0f, 1f)
}

internal class DoubleProvider : Provider<Double> {
    override fun provide(): List<Double> = listOf(0.0, 1.0)
}

internal class BooleanProvider : Provider<Boolean> {
    override fun provide(): List<Boolean> = listOf(true, false)
}

internal class StringProvider : Provider<String> {
    override fun provide(): List<String> = listOf("", "Sample", "Sample".repeat(10))
}

// Custom Providers
internal class AppbarLeadingButtonProvider : Provider<@Composable (AppbarLeadingButtonScope.() -> Unit)> {
    override fun provide(): List<@Composable (AppbarLeadingButtonScope.() -> Unit)> {
        return listOf(
            {
                PopBackIcon {}
            },
        )
    }
}

internal class AppbarTrailingElementsProvider : Provider<@Composable (AppbarTrailingElementsScope.() -> Unit)> {
    override fun provide(): List<@Composable (AppbarTrailingElementsScope.() -> Unit)> {
        return listOf(
            {
                SearchIcon { }
            },
            {
                MoreVertIcon { }
            }
        )
    }
}
