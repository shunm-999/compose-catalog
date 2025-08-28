plugins {
    id("com.shunm.android.compose_catalog.primitive.kmp")
    id("com.shunm.android.compose_catalog.primitive.kmp.ios")
    id("com.shunm.android.compose_catalog.primitive.spotless")
    alias(libs.plugins.jetbrains.kotlin.serialization )
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(libs.kotlinxSerializationJson)
            api(libs.kotlinxDatetime)
            api(libs.kotlinxCollectionsImmutable)
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
        }
    }
}