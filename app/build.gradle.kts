import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("plugin.serialization") version "1.9.22"
}

var secretPropertiesFile: File = rootProject.file("app/secrets.properties")!!
val secretProperties = Properties()
try {
    secretProperties.load(FileInputStream(secretPropertiesFile))
    Logging.getLogger("SECRETS_LOAGER").info("Using secrets.properties")
} catch (e: FileNotFoundException) {
    secretPropertiesFile = rootProject.file("app/secrets.properties.example")!!
    secretProperties.load(FileInputStream(secretPropertiesFile))
    Logging.getLogger("SECRETS_LOAGER").warn("Compiling with example secrets.properties")
}

android {
    namespace = "ru.lavafrai.maiapp"
    compileSdk = 34
    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "ru.lavafrai.maiapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "0.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "APP_METRIKA_API_KEY", secretProperties.getProperty("appMetrikaApiKey") ?: "null")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("org.jsoup:jsoup:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("io.appmetrica.analytics:analytics:6.1.0")
}