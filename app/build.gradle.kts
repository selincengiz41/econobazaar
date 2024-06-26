plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.parcelize)
   alias(libs.plugins.google.services)
}

android {
    namespace = "com.selincengiz.econobazaar"
    compileSdk = 34

    dataBinding {
        enable = true
    }

    defaultConfig {
        applicationId = "com.selincengiz.econobazaar"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":cart:domain"))
    implementation(project(":cart:data"))
    implementation(project(":cart:presentation"))
    implementation(project(":categories:domain"))
    implementation(project(":categories:data"))
    implementation(project(":categories:presentation"))
    implementation(project(":detail:domain"))
    implementation(project(":detail:data"))
    implementation(project(":detail:presentation"))
    implementation(project(":favorite:domain"))
    implementation(project(":favorite:data"))
    implementation(project(":favorite:presentation"))
    implementation(project(":home:domain"))
    implementation(project(":home:data"))
    implementation(project(":home:presentation"))
    implementation(project(":onboarding:domain"))
    implementation(project(":onboarding:data"))
    implementation(project(":onboarding:presentation"))
    implementation(project(":profile:domain"))
    implementation(project(":profile:data"))
    implementation(project(":profile:presentation"))
    implementation(project(":search:domain"))
    implementation(project(":search:data"))
    implementation(project(":search:presentation"))
    implementation(project(":login:domain"))
    implementation(project(":login:data"))
    implementation(project(":login:presentation"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.messaging.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kapt (libs.androidx.room.compiler)

    // SSP-SDP library
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Glide
    implementation(libs.glide)

    //Roundable Layout
    implementation(libs.roundableLayout)

    //Lottie
    implementation(libs.lottie)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    //Datastore
    implementation(libs.androidx.datastore.preferences)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Drawer
    implementation (libs.minavdrawer)

    //Firebase
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
}