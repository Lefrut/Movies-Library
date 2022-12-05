package ru.dashkevich.viewapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dashkevich.viewapp.data.api.movies.MoviesService
import ru.dashkevich.viewapp.data.api.movies.util.MOVIE_URL
import ru.dashkevich.viewapp.util.log.logE


val retrofitModule = module {

    single<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(MOVIE_URL)
            .addConverterFactory(get())
            .build()
    }

    single<MoviesService> {
        get<Retrofit>().create(MoviesService::class.java)
    }

}

val dataStoreModule = module {



}

val appModules = listOf(
    dataStoreModule, retrofitModule
)