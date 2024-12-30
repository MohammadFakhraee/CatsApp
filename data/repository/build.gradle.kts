plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.repository"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "com.mohammadhf.repository.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    projects.apply {
        implementation(data.local)
        implementation(data.remote)
    }
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.androidx.core.executor.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.google.truth)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
    // Room testing
    androidTestImplementation(libs.androidx.room.runtime)
    androidTestImplementation(libs.androidx.room.testing)
    // OkHttp testing
    androidTestImplementation(libs.mockwebserver)
    // Retrofit testing
    androidTestImplementation(libs.retrofit)
    androidTestImplementation(libs.retrofit.converter.gson)
}