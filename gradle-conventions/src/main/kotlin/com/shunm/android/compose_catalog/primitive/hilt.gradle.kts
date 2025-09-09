package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs

plugins {
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

dependencies {
    add("implementation", libs.library("hilt-android"))
    add("ksp", libs.library("hilt-compiler"))
}
