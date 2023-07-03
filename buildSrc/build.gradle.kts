plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Dependencies for src.main.kotlin.HtmlTableTask
    implementation("org.codehaus.groovy:groovy-json:3.0.9")
    implementation(gradleApi())
}
