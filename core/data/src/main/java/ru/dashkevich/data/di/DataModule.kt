package ru.dashkevich.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dashkevich.data.api.MoviesService
import ru.dashkevich.utility.constants.DATASTORE_FILE
import ru.dashkevich.utility.constants.MOVIE_URL


val retrofitModule = module {

    single<Converter.Factory> {
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

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile(DATASTORE_FILE) },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

}

val dataModules = module {
    includes(dataStoreModule, retrofitModule)
}