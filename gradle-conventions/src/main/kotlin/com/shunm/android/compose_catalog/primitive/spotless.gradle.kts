package com.shunm.android.compose_catalog.primitive

import com.diffplug.gradle.spotless.SpotlessExtension
import com.shunm.android.compose_catalog.util.libs
import com.shunm.android.compose_catalog.util.version

plugins {
    id("com.diffplug.spotless")
}

configure<SpotlessExtension> {
    kotlin {
        target("src/**/*.kt")
        ktlint(libs.version("ktlint"))
    }
}