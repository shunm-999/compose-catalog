rootProject.name = "Composecatalog"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("gradle-conventions")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        // navigation3 adaptive
        maven {
            url = uri("https://androidx.dev/snapshots/builds/13508953/artifacts/repository")
        }
    }
}

include(":composeApp")
include(":infra:github")
include(":domain:category")
include(":domain:component")
include(":domain:github")
include(":presentation:shared")
include(":presentation:component")
include(":presentation:github")
include(":tools:ksp-processor")
