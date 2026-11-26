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

    testImplementation(kotlin("test-junit5"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Gradle does not run buildSrc tests as part of the main build; run them with ./gradlew -p buildSrc test
tasks.test {
    useJUnitPlatform()
}
