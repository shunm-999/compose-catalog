package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlin.reflect.KClass

@Composable
internal fun rememberNavController(): NavController {
    val backStack = remember { SnapshotStateList<NavRoute>() }
    return NavController(backStack)
}

internal class NavController(
    private val backStack: SnapshotStateList<NavRoute>
) {
    fun navigate(route: NavRoute, builderAction: NavOptionBuilder.() -> Unit = {}) {
        val navOption = NavOptionBuilder().apply(builderAction).build()

        if (navOption.popUpTo != null) {
            val currentTop = backStack.find { route -> route::class == navOption.popUpTo.route }
            if (currentTop != null) {
                val index = backStack.indexOf(currentTop)
                val removeUpToIndex = if (navOption.popUpTo.inclusive) {
                    index
                } else {
                    index + 1
                }

                if (removeUpToIndex < backStack.size) {
                    backStack.removeRange(removeUpToIndex, backStack.size)
                }
            }
        }

        if (navOption.singleTop != null && backStack.lastOrNull()
                ?.let { route::class == it::class } == true
        ) {
            // Do nothing
        } else {
            backStack.add(route)
        }
    }

    fun popBackStack(): NavRoute? {
        return backStack.removeLastOrNull()
    }
}

internal data class NavOption(
    val popUpTo: PopUpTo?,
    val singleTop: SingleTop?
) {

    data class PopUpTo(
        val route: KClass<out NavRoute>,
        val inclusive: Boolean = false
    )

    object SingleTop
}

class NavOptionBuilder {
    private var popUpTo: NavOption.PopUpTo? = null
    var singleTop: Boolean? = null

    fun popUpTo(route: KClass<out NavRoute>, inclusive: Boolean = false) {
        popUpTo = NavOption.PopUpTo(route, inclusive)
    }

    internal fun build(): NavOption {
        return NavOption(popUpTo, if (singleTop == true) NavOption.SingleTop else null)
    }
}

class PopUpToBuilder {
    var inclusive: Boolean = false
}

inline fun <reified T : NavRoute> NavOptionBuilder.popUpTo(builderAction: PopUpToBuilder.() -> Unit = {}) {
    val popUpToBuilder = PopUpToBuilder().apply(builderAction)
    popUpTo(route = T::class, inclusive = popUpToBuilder.inclusive)
}

