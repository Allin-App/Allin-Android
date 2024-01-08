buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://maven.openium.fr/") }
    }

    dependencies {
        // Gradle
        classpath(libs.plugin.gradle)

        // Kotlin
        classpath(libs.plugin.kotlin)

        // Kotlin Serialization
        classpath(libs.plugin.kotlinSerialization)

        // Hilt
        classpath(libs.plugin.hilt)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

subprojects {
    tasks {
        withType(JavaCompile::class.java).configureEach {
            sourceCompatibility = JavaVersion.VERSION_1_8.name
            targetCompatibility = JavaVersion.VERSION_1_8.name
        }

        withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                freeCompilerArgs += listOf(
                    "-Xallow-result-return-type",
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlin.ExperimentalStdlibApi",
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlin.time.ExperimentalTime",
                    "-opt-in=kotlinx.coroutines.FlowPreview"
                )
            }
        }
    }
}