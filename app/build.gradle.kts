plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.to_do_list"
    compileSdk = 34
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.to_do_list"
        minSdk = 27
        targetSdk = 34
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
//    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    //Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.sqlite:sqlite:2.4.0")
    implementation("com.google.android.material:material:1.5.0")
    //loitte naimation
    implementation("com.airbnb.android:lottie:4.2.2")
    implementation("com.google.code.gson:gson:2.10.1")
}