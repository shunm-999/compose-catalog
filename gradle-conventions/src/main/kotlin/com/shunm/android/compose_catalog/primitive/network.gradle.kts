package com.shunm.android.compose_catalog.primitive

import com.shunm.android.compose_catalog.util.kotlin
import com.shunm.android.compose_catalog.util.library
import com.shunm.android.compose_catalog.util.libs

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.library("kotlinxSerializationGradlePlugin"))
        }
        androidMain.dependencies {
            implementation(libs.library("retrofit"))
            implementation(libs.library("okhttp"))
            implementation(libs.library("okhttpCore"))
            implementation(libs.library("okhttpLoggingInterceptor"))
        }
    }
}
