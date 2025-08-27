package com.shunm.android.compose_catalog.navigation

interface NavRoute

interface NavPag : NavRoute

interface NagGraph : NavRoute

object ComponentList

data class ComponentDetail(val id: Int) : NavPag