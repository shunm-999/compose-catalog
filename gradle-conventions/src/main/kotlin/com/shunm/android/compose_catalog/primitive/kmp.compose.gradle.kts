package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.commonMainImplementation
import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    commonMainImplementation(compose.ui)
    commonMainImplementation(compose.components.uiToolingPreview)
    commonMainImplementation(compose.materialIconsExtended)
    commonMainImplementation(libs.library("material3"))
}

dependencies {
    add("debugImplementation", compose.uiTooling)
}