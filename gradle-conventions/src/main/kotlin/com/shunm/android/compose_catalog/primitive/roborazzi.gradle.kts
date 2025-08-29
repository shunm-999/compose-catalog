package com.shunm.android.compose_catalog.primitive

import com.android.build.gradle.BaseExtension
import com.shunm.android.compose_catalog.util.getDefaultPackageName
import com.shunm.android.compose_catalog.util.kotlin
import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure

plugins {
    id("io.github.takahirom.roborazzi")
}

configure<RoborazziExtension> {
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf(getDefaultPackageName(project.name))
        includePrivatePreviews = true
        robolectricConfig = mapOf(
            // Roborazzi 1.46.1 uses SDK 33 by default, which causes compatibility issues with coil-ktor3
            // resulting in a JNI ClassNotFoundException on CI.
            // To avoid this, we explicitly set the SDK version to 35.
            // See https://github.com/coil-kt/coil/issues/3049 for details.
            "sdk" to "[35]",
            "qualifiers" to "RobolectricDeviceQualifiers.Pixel4a",
        )
    }
}

configure<BaseExtension> {
    testOptions.unitTests.all {
        it.systemProperties["robolectric.graphicsMode"] = "NATIVE"
        it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
    }
}


kotlin {
    sourceSets {
        androidUnitTest.dependencies {
            implementation(libs.library("robolectric"))
            implementation(libs.library("androidx-espresso-core"))
            implementation(libs.library("junit"))
            implementation(libs.library("androidx-testExt-junit"))
            implementation(libs.library("kotlin-test"))
            implementation(libs.library("kotlin-testJunit"))

            implementation(libs.library("androidx-uiTest-junit4"))
            implementation(libs.library("androidx-uiTest-manifest"))

            implementation(libs.library("roborazzi"))
            implementation(libs.library("roborazziCompose"))
            implementation(libs.library("roborazziPreviewScannerSupport"))
            implementation(libs.library("composablePreviewScannerAndroid"))
        }
    }
}