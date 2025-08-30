plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.shunm.android.compose_catalog.primitive.spotless")
}

dependencies {
    implementation(libs.kspSymbolProcessingApi)
}
