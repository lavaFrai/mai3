import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("plugin.serialization") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
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
    /*composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }*/
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val ktor_version = "2.3.12"

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")
    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation("androidx.glance:glance-appwidget:1.1.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.browser:browser:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("org.jsoup:jsoup:1.18.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("io.appmetrica.analytics:analytics:6.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    implementation("me.saket.telephoto:zoomable-image-coil:0.8.0")
    implementation("eu.bambooapps:compose-material3-pullrefresh:1.1.0")

    implementation("br.com.devsrsouza.compose.icons:simple-icons:1.1.0")
    implementation("br.com.devsrsouza.compose.icons:feather:1.1.0")
    implementation("br.com.devsrsouza.compose.icons:line-awesome:1.1.0")

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.mohamedrejeb.ksoup:ksoup-html:0.3.1")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("com.github.lavaFrai:maiapi:v2.0.9")
    implementation("com.github.lavaFrai:exler-maiapi:v1.0.6")
    implementation("com.github.lavaFrai:maiApplicantsParser2024:bd259f971d")
    implementation("com.github.lavaFrai:maiaccount:6ea88e94db")
}