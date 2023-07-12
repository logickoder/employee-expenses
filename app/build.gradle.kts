@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 34
    namespace = "dev.logickoder.expensemanager"

    defaultConfig.apply {
        applicationId = namespace
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes.apply {
        maybeCreate("release").apply {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions.apply {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures.apply {
        compose = true
    }

    composeOptions.apply {
        kotlinCompilerExtensionVersion = rootProject.libs.versions.compose.compiler.get()
    }

    packagingOptions.apply {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // for using some java 8 classes like LocalDate with older versions of android
    coreLibraryDesugaring(libs.core.java8)

    // Core
    implementation(libs.core)
    implementation(libs.core.appcompat)
    implementation(libs.core.constraintlayout)
    implementation(libs.core.material)

    // Google Accompanist
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.placeholdermaterial)

    // Coil
    implementation(libs.coil)

    // Compose
    implementation(platform(libs.compose))
    implementation(libs.compose.activity)
    implementation(libs.compose.constraintlayout)
    debugImplementation(libs.compose.custom.view)
    debugImplementation(libs.compose.custom.view.pooling)
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(platform(libs.compose))
    androidTestImplementation(libs.compose.ui.test.junit)

    // lifecycle
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)

    // preferences datastore
    implementation(libs.datastore)

    // Kotlinx
    implementation(libs.kotlin.immutable)
    implementation(libs.kotlin.serialization)

    // Ktor - for network calls
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.contentnegotiation)
    implementation(libs.ktor.serialization)

    // Napier
    implementation(libs.napier)

    // Room
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // Voyager
    implementation(libs.voyager)
    implementation(libs.voyager.android)

    testImplementation(libs.junit)
    testImplementation(libs.junit.engine)
    androidTestImplementation(libs.expresso)
    androidTestImplementation(libs.junit.android)
}