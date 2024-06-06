import org.gradle.language.nativeplatform.internal.BuildType
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.starter.easylauncher")
}

// Keystore
val keystorePropertiesFile = rootProject.file("keys/keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "fr.iut.alldev.allin"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "fr.iut.alldev.allin"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    signingConfigs {
        named(BuildType.DEBUG.name) {
            storeFile = file(keystoreProperties["debugStoreFile"].toString())
        }
        register(BuildType.RELEASE.name) {
            storeFile = file(keystoreProperties["releaseStoreFile"].toString())
            storePassword = keystoreProperties["passwordRelease"].toString()
            keyAlias = keystoreProperties["aliasRelease"].toString()
            keyPassword = keystoreProperties["passwordRelease"].toString()
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName(BuildType.DEBUG.name)
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName(BuildType.RELEASE.name)
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += listOf("env")
    productFlavors {
        create("dev") {
            dimension = "env"
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".dev"
        }

        create("prod") {
            dimension = "env"
        }
    }
}

easylauncher {
    buildTypes {
        register(BuildType.DEBUG.name).configure {
            filters(redRibbonFilter())
        }
    }

    productFlavors {
        register("dev") {
            filters(chromeLike())
        }
    }
}

dependencies {
    implementation(project(":data"))

    // Android
    implementation(libs.bundles.android)

    // Lifecycle
    implementation(libs.bundles.androidx.lifecycle)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Timber
    implementation(libs.timber)

    // Squircle
    implementation(libs.smoothCornerRect)

    // Coil
    implementation(libs.coil.compose)

    // Tests
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.androidx.junit)
    androidTestImplementation(libs.hilt.androidTesting)
    kaptAndroidTest(libs.hilt.androidCompiler)
    androidTestImplementation(libs.ui.test.junit)
    debugImplementation(libs.ui.test.manifest)
}