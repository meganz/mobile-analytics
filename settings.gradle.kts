pluginManagement {
    val kspVersion: String by settings
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins{
        id("com.google.devtools.ksp") version kspVersion apply false
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
