package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.kotlin
import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs

plugins {
    id("io.github.takahirom.roborazzi")
}

kotlin {
    sourceSets {
        androidUnitTest.dependencies {
            implementation(libs.library("robolectric"))
            implementation(libs.library("androidx-espresso-core"))
            implementation(libs.library("junit"))

            implementation(libs.library("roborazzi"))
            implementation(libs.library("roborazziCompose"))
            implementation(libs.library("roborazziPreviewScannerSupport"))
        }
    }
}