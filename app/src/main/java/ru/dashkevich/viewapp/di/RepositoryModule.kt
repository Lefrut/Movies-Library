package ru.dashkevich.viewapp.di

import org.koin.dsl.module
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.data.repository.MoviesRepository

val repositoryModule = module {

    single { MoviesRepository(get()) }
    single { DataStoreRepository(get()) }

}