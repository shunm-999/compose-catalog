import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.shunm.android.compose_catalog.primitive.spotless")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kspSymbolProcessingApi)
}

