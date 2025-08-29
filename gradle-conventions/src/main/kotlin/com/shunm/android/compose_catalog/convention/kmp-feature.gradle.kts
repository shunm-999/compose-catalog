package com.shunm.android.compose_catalog.convention

import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs
import gradle.kotlin.dsl.accessors._937c9308645c5e8bdf2065b840746083.implementation
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.shunm.android.compose_catalog.primitive.kmp")
    id("com.shunm.android.compose_catalog.primitive.kmp.ios")
    id("com.shunm.android.compose_catalog.primitive.kmp.compose")
    id("com.shunm.android.compose_catalog.primitive.kmp.compose.resources")
    id("com.shunm.android.compose_catalog.primitive.spotless")
    id("com.google.devtools.ksp")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.library("androidx-activity-compose"))

            // Navigation3
            implementation(libs.library("androidx-navigation3-ui"))
            implementation(libs.library("androidx-navigation3-runtime"))
            implementation(libs.library("androidx-lifecycle-viewmodel-navigation3"))
            implementation(libs.library("androidx-material3-adaptive-navigation3"))
            implementation(libs.library("kotlinx-serialization-core"))
        }
        commonMain.dependencies {
            // describe shared module
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.library("androidx-lifecycle-viewmodelCompose"))
            implementation(libs.library("androidx-lifecycle-runtimeCompose"))
            implementation(libs.library("kotlinxCollectionsImmutable"))
        }

        commonTest.dependencies {
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(compose.material3)
        }

    }

    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=androidx.compose.ui.test.ExperimentalTestApi",
        )
    }
}