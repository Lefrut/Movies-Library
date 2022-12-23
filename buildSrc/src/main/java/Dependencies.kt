object Dependencies {


    object Plugins {
        const val library = "com.android.library"
        const val application = "com.android.application"
        const val kotlinAndroid = "android"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val kapt = "kapt"
    }

    object ClassPath {

        object Version {
            const val gradle = "7.3.1"
            const val kotlin = "1.7.20"
            const val navigationSafeArgs = "2.5.3"
        }

        const val gradlePlugin = "com.android.tools.build:gradle:${Version.gradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigationSafeArgs}"

    }

    object Android {

        object Version {
            const val coreKtx = "1.9.0"
            const val appCompat = "1.5.1"
            const val material = "1.7.0"
            const val constraintLayout = "2.1.4"
            const val legacySupportV4 = "1.0.0"
            const val lifecycle = "2.5.1"
            const val navigation = "2.5.3"
            const val fragment = "1.5.4"
            const val viewPager = "1.0.0"
            const val room = "2.4.3"
            const val paging = "3.1.1"
        }

        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val material = "com.google.android.material:material:${Version.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Version.legacySupportV4}"
        const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val viewPager = "androidx.viewpager2:viewpager2:${Version.viewPager}"
        const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
        const val roomCoroutines = "androidx.room:room-ktx:${Version.room}"
        const val roomPaging = "androidx.room:room-paging:${Version.room}"
        const val paging3 = "androidx.paging:paging-runtime-ktx:${Version.paging}"


    }

    object AndroidTest {

        object Version {
            const val junit = "4.13.2"
            const val androidJunit = "1.1.4"
            const val espresso = "3.5.0"
        }


        const val junit = "junit:junit:${Version.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Version.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.junit}"

    }

    object Other {

        object Version {

            const val dataStore = "1.0.0"
            const val retrofit = "2.9.0"
            const val okHttp = "4.10.0"
            const val coil = "2.2.2"
            const val koin = "3.2.2"
            const val koinAndroid = "3.3.0"
        }

        const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Version.dataStore}"
        const val dataStoreCore = "androidx.datastore:datastore-core:${Version.dataStore}"

        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp-bom:${Version.okHttp}"

        const val coil = "io.coil-kt:coil:${Version.coil}"

        const val koin = "io.insert-koin:koin-core:${Version.koin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Version.koinAndroid}"


    }

}