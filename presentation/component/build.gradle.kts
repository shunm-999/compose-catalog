plugins {
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.roborazzi")
}

dependencies {
    implementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.shared)
    implementation(project(":domain:component"))

    commonMainImplementation(libs.coil)
    commonMainImplementation(libs.coilNetwork)

    add("kspCommonMainMetadata", project(":tools:ksp-processor"))
}
