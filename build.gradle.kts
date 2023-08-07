plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("org.jetbrains.dokka").version("1.8.20").apply(false)
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

buildscript {

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.8.20")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
