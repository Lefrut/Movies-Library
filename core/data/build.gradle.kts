plugins {
    id(Dependencies.Plugins.library)
    kotlin(Dependencies.Plugins.kotlinAndroid)
    kotlin(Dependencies.Plugins.kapt)


}


android {
    namespace = "ru.dashkevich.data"
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
}

dependencies {

    implementation(project("path" to ":utility"))

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    testImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.androidJunit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)

    implementation(Dependencies.Other.dataStorePreferences)
    implementation(Dependencies.Other.dataStoreCore)

    implementation(Dependencies.Other.retrofit)
    implementation(Dependencies.Other.retrofitConverter)
    implementation(Dependencies.Other.okHttp)

    implementation(Dependencies.Android.roomRuntime)
    kapt(Dependencies.Android.roomCompiler)
    implementation(Dependencies.Android.roomCoroutines)
    implementation(Dependencies.Android.roomPaging)
    implementation(Dependencies.Android.paging3)


    //DI Koin
    implementation(Dependencies.Other.koin)
    implementation(Dependencies.Other.koinAndroid)
}