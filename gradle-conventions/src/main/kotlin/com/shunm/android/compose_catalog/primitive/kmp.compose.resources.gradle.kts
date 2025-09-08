package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.commonMainImplementation
import com.shunm.android.compose_catalog.util.getDefaultPackageName
import org.gradle.internal.extensions.stdlib.capitalized

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    commonMainImplementation(compose.components.resources)
}

compose {
    resources {
        if (project.path.contains("presentation")) {
            val namespace = getDefaultPackageName(project.name)
            packageOfResClass = namespace
            nameOfResClass = namespace.split(".").last().capitalized() + "Res"
            generateResClass = always
        }
    }
}
