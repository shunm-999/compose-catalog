plugins {
    kotlin("plugin.serialization") version "2.2.10"
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.hilt")
    id("com.shunm.android.compose_catalog.primitive.network")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain.shared)
            implementation(libs.kotlinxSerializationJson)
        }
        androidMain.dependencies {
            implementation(projects.domain.github)
            implementation(libs.kotlinxSerializationJson)
        }
    }
}
