import com.android.build.api.dsl.BuildFeatures

plugins {
    id(Dependencies.Plugins.library)
    kotlin(Dependencies.Plugins.kotlinAndroid)
    id(Dependencies.Plugins.navigationSafeArgs)
}

android {
    namespace = "ru.dashkevich.authorization"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project("path" to ":utility"))
    implementation(project("path" to ":core:domain"))
    implementation(project("path" to ":core:data"))



    //Fragments
    implementation(Dependencies.Android.fragment)
    //MaterialComponents
    implementation(Dependencies.Android.material)
    //Jetpack Navigation
    implementation(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.Android.navigationUIKtx)
    //DI Koin
    implementation(Dependencies.Other.koin)
    implementation(Dependencies.Other.koinAndroid)

    implementation(Dependencies.Android.lifecycleLivedata)
    implementation(Dependencies.Android.lifecycleViewModel)

    //DataStore
    implementation(Dependencies.Other.dataStorePreferences)
    implementation(Dependencies.Other.dataStoreCore)

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    testImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.androidJunit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)
}