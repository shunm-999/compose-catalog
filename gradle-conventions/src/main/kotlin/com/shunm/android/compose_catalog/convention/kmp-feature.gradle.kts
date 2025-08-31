package com.shunm.android.compose_catalog.convention

import com.android.build.gradle.BaseExtension
import com.google.devtools.ksp.gradle.KspAATask
import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

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
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3ExpressiveApi",
        )
    }
}

dependencies {
    add("kspCommonMainMetadata", project(":tools:ksp-processor"))
    add("debugImplementation", compose.uiTooling)
}

configure<BaseExtension> {
    testOptions.unitTests.isIncludeAndroidResources = true
}

configure<KotlinMultiplatformExtension> {
    sourceSets {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
    }
}

// Workaround for KSP declarations become Unresolved reference in IDE.
// https://github.com/google/ksp/issues/963#issuecomment-2970973355
tasks.withType<KspAATask>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

tasks.withType<KotlinCompile>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

tasks.withType<KotlinNativeCompile>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
