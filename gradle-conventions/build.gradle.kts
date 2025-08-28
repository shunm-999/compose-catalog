plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.bundles.plugins)
    // Not documented in README, but required to import buildkonfig.compiler. https://github.com/yshrsmz/BuildKonfig/issues/227
    implementation(libs.buildkonfigCompiler)
}