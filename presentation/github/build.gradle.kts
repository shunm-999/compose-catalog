plugins {
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.roborazzi")
}

dependencies {
    implementation(projects.domain.component)
    implementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.component)
}
