package com.shunm.android.compose_catalog.util

internal fun getDefaultPackageName(moduleName: String): String {
    return "com.shunm.android.compose_catalog.${moduleName.replace("-", "_")}"
}