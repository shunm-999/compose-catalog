import org.gradle.kotlin.dsl.invoke

plugins {
    id("com.shunm.android.compose_catalog.convention.kmp-feature")
    id("com.shunm.android.compose_catalog.primitive.roborazzi")
}

dependencies {
    implementation(projects.presentation.shared)
    commonMainImplementation(projects.presentation.shared)
    implementation(project(":domain:component"))

    add("kspCommonMainMetadata", project(":tools:ksp-processor"))
}
