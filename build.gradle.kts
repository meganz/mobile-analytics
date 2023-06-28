plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
    id("digital.wup.android-maven-publish").version("3.6.2").apply(false)
    `maven-publish`
}

allprojects {
    group = "mega.privacy.mobile"
    version = "1.0.0"
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
