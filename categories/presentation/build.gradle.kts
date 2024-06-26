plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.navigationSafeArgs)

}

android {
    namespace = "com.selincengiz.categories.presentation"
    compileSdk = 34

    dataBinding {
        enable = true
    }
    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":detail:presentation"))
    implementation(project(":search:presentation"))
    implementation(project(":core:common"))
    implementation(project(":categories:domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Paging
    implementation (libs.androidx.paging.common.ktx)
    implementation (libs.androidx.paging.runtime.ktx)

    // SSP-SDP library
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    //Roundable Layout
    implementation(libs.roundableLayout)
}