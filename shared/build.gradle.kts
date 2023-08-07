//import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.21"
    id("com.squareup.sqldelight")
    id("org.jetbrains.dokka")
}

group = "com.example.kmmtestapp"
version = "1.0"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    val coroutinesVersion = "1.7.1"
    val ktorVersion = "2.3.2"
    val sqlDelightVersion = "1.5.5"
    val dateTimeVersion = "0.4.0"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.9.0")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting
    }
}

android {
    namespace = "com.example.kmmtestapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.example.kmmtestapp.kmm.shared.cache"
    }
}