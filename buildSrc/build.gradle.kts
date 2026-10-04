plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    // Dependencies for src.main.kotlin.HtmlTableTask (groovy.json ships with Gradle's bundled Groovy)
    implementation(localGroovy())
    implementation(gradleApi())
}
