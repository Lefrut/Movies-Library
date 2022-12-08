package ru.dashkevich.domain.di

import org.koin.dsl.module
import ru.dashkevich.domain.repository.DataStoreRepository
import ru.dashkevich.domain.repository.MoviesRepository

val repositoryModule = module {

    single { MoviesRepository(get()) }
    single { DataStoreRepository(get()) }

}

val domainModules = module { includes(repositoryModule) }