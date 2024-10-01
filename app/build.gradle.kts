import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose)
}

var secretPropertiesFile: File = rootProject.file("app/secrets.properties")
val secretProperties = Properties()
try {
    secretProperties.load(FileInputStream(secretPropertiesFile))
    Logging.getLogger("SECRETS_LOAGER").info("Using secrets.properties")
} catch (e: FileNotFoundException) {
    secretPropertiesFile = rootProject.file("app/secrets.properties.example")
    secretProperties.load(FileInputStream(secretPropertiesFile))
    Logging.getLogger("SECRETS_LOAGER").warn("Compiling with example secrets.properties")
}

android {
    namespace = "ru.lavafrai.maiapp"
    compileSdk = 34
    android.buildFeatures.buildConfig = true

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
        applicationId = "ru.lavafrai.maiapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 10_000

        versionName = "0.10.0b"

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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.compose.material3.android)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.browser)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.jsoup)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.analytics)
    implementation(libs.okhttp)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.zoomable.image.coil)
    implementation(libs.compose.material3.pullrefresh)

    implementation(libs.simple.icons)
    implementation(libs.feather)
    implementation(libs.line.awesome)

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.ksoup.html)
    implementation(libs.commons.text)
    implementation(libs.coil.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    implementation(libs.maiapi)
    implementation(libs.exler.maiapi)
    implementation(libs.maiapplicantsparser2024)
    implementation(libs.maiaccount)
}