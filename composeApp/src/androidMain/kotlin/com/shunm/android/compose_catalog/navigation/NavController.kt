package com.shunm.android.compose_catalog.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

internal class NavController(
    private val backStack: SnapshotStateList<NavRoute>
) {
    fun navigate(route: NavRoute, option: NavOption = NavOption.Default) {
        when (option) {
            is NavOption.PopUpTo -> {
                val index = backStack.indexOf(option.route)
                if (index != -1) {
                    backStack.removeRange(index + 1, backStack.size)
                }
                backStack.add(route)
            }

            is NavOption.SingleTop -> {
                if (backStack.contains(route)) {
                    val index = backStack.indexOf(route)
                    backStack.removeRange(index, backStack.size)
                } else {
                    backStack.add(route)
                }
            }

            is NavOption.Default -> {
                backStack.add(route)
            }
        }

    }

    fun popBackStack(): NavRoute? {
        return backStack.removeLastOrNull()
    }
}

internal sealed interface NavOption {
    object Default : NavOption
    data class PopUpTo(val route: NavRoute) : NavOption
    object SingleTop : NavOption
}

@Composable
internal fun rememberNavController(): NavController {
    val backStack = remember { SnapshotStateList<NavRoute>() }
    return NavController(backStack)
}