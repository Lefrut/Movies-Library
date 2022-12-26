plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.navigationSafeArgs)
    kotlin(Dependencies.Plugins.kotlinAndroid)

}

android {
    namespace = "ru.dashkevich.viewapp"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    implementation(project(":features:authorization"))
    implementation(project(":features:library"))
    implementation(project(":features:unknown"))
    implementation(project(":features:profile"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    //Android
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.legacySupportV4)
    implementation(Dependencies.Android.lifecycleLivedata)
    implementation(Dependencies.Android.lifecycleViewModel)
    //Fragments
    implementation(Dependencies.Android.fragment)
    //MaterialComponents
    implementation(Dependencies.Android.material)
    //Jetpack Navigation
    implementation(Dependencies.Android.navigationFragmentKtx)
    implementation(Dependencies.Android.navigationUIKtx)
    //Android Test
    testImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.androidJunit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)


    //DataStore
    implementation(Dependencies.Other.dataStorePreferences)
    implementation(Dependencies.Other.dataStoreCore)

    //Internet
    implementation(Dependencies.Other.retrofit)
    implementation(Dependencies.Other.retrofitConverter)
    implementation(Dependencies.Other.okHttp)

    //Images & coil
    implementation(Dependencies.Other.coil)

    //DI Koin
    implementation(Dependencies.Other.koin)
    implementation(Dependencies.Other.koinAndroid)
}