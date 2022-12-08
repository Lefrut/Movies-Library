import com.android.build.api.dsl.BuildFeatures

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Dependencies.Plugins.navigationSafeArgs)
}

android {
    namespace = "ru.dashkevich.authorization"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

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
    implementation(project("path" to ":utility"))
    implementation(project("path" to ":features:library"))
    implementation(project("path" to ":features:unknown"))
    implementation(project("path" to ":features:profile"))
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}