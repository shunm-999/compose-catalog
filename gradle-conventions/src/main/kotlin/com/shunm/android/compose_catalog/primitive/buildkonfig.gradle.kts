package com.shunm.android.compose_catalog.primitive

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    id("com.codingfeline.buildkonfig")
}

buildkonfig {
    packageName = "com.shunm.android.compose_catalog"

    defaultConfigs {
        buildConfigField(STRING, "versionName", "1.0.0")
    }
}
