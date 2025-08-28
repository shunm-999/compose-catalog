package com.shunm.android.compose_catalog.util

internal fun getDefaultPackageName(moduleName: String): String {
    return "io.github.droidkaigi.confsched.${moduleName.replace("-", "_")}"
}