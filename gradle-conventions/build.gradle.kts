plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.bundles.plugins)
    implementation(libs.buildkonfigCompiler)
    implementation(libs.hilt.android.gradle.plugin)
}
