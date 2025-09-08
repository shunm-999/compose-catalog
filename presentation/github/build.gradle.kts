plugins {
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.roborazzi")
    id("com.shunm.android.compose_catalog.primitive.hilt")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktorClientOkhttp)
        }
    }
}

dependencies {
    implementation(projects.domain.shared)
    implementation(projects.domain.component)
    implementation(projects.domain.github)

    commonMainImplementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.component)

    commonMainImplementation(libs.coil)
    commonMainImplementation(libs.coilNetwork)
}
