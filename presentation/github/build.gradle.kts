plugins {
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.roborazzi")
    id("com.shunm.android.compose_catalog.primitive.hilt")
}

dependencies {
    implementation(projects.domain.shared)
    implementation(projects.domain.component)
    implementation(projects.domain.github)
    implementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.component)
}
