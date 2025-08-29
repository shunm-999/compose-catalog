package com.shunm.android.compose_catalog.util

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.kotlin(
    block: KotlinMultiplatformExtension.() -> Unit
) {
    extensions.getByType<KotlinMultiplatformExtension>().apply(block)
}

internal fun KotlinMultiplatformExtension.androidLibrary(
    block: KotlinMultiplatformAndroidLibraryExtension.() -> Unit
) {
    extensions.getByType<KotlinMultiplatformAndroidLibraryExtension>().apply(block)
}

internal fun DependencyHandlerScope.commonMainTestImplementation(
    dependencyNotation: Any
) {
    add("commonTestImplementation", dependencyNotation)
}

internal fun DependencyHandlerScope.commonMainImplementation(
    dependencyNotation: Any
) {
    add("commonMainImplementation", dependencyNotation)
}