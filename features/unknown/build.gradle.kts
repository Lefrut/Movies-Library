plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.navigationSafeArgs)
    kotlin(Dependencies.Plugins.kotlinAndroid)
}

android {
    namespace = "com.example.unknown"
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


    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.legacySupportV4)
    implementation(Dependencies.Android.lifecycleLivedata)
    implementation(Dependencies.Android.lifecycleViewModel)

    //Jetpack Navigation
    implementation(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.Android.navigationUIKtx)

    //DI Koin
    implementation(Dependencies.Other.koin)
    implementation(Dependencies.Other.koinAndroid)

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    testImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.androidJunit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)
}