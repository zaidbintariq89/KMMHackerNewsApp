plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "com.mobilelive.looking4app"
version = "1.0.0"
application {
    mainClass.set("com.mobilelive.looking4app.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

ktor {
    fatJar {
        archiveFileName.set("server.jar")
    }
}

dependencies {
    implementation(projects.shared)

    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.exposed.core)
    implementation(libs.ktor.exposed.dao)
    implementation(libs.ktor.exposed.jdbc)
    implementation(libs.postgresql.database)
//    implementation(libs.ktor.auth.jwt)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}