package com.shunm.android.compose_catalog.navigation

import kotlinx.serialization.Serializable

@Serializable
data object CategoryListRoute : NavRoute

@Serializable
data object ComponentListRoute : NavRoute

@Serializable
data class ComponentDetailRoute(val componentId: String) : NavRoute