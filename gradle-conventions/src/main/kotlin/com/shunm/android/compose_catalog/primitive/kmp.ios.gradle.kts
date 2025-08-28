package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.kotlin

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}
