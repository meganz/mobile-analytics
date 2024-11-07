pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mobile-Analytics"
include(":shared")
include(":analytics-annotations")
include(":analytics-processor")
include(":analytics-core")
