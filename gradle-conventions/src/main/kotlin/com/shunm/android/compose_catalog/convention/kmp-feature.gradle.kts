package com.shunm.android.compose_catalog.convention

import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs
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
        commonMain.dependencies {
            // describe shared module

            implementation(libs.library("kotlinxCollectionsImmutable"))
        }

        commonTest.dependencies {
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(libs.library("kotlinTest"))
        }

    }

    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=androidx.compose.ui.test.ExperimentalTestApi",
        )
    }
}