package ru.dashkevich.viewapp.di

import android.content.Context
import org.koin.dsl.module
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.util.constants.dataStore

val repositoryModule = module {

    single { MoviesRepository(get()) }
    single { DataStoreRepository(get<Context>().dataStore) }

}