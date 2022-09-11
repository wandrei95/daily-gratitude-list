plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.andrei.wegroszta.dailygratitudelist"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://somereleaseurl.com/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://sometesturl.com/\"")
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("com.google.android.flexbox:flexbox:3.0.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    implementation("androidx.activity:activity-ktx:1.5.0")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

    //hilt
    implementation("com.google.dagger:hilt-android:2.40.5")
    kapt("com.google.dagger:hilt-compiler:2.40.5")

    // db - room
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.3.2")

    // networking - retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // images - glide
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
