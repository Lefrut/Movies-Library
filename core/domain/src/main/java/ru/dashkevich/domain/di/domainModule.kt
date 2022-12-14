package ru.dashkevich.domain.di

import android.graphics.Movie
import org.koin.dsl.module
import ru.dashkevich.domain.paging.MoviesPagingSource
import ru.dashkevich.domain.repository.DataStoreRepository
import ru.dashkevich.domain.repository.MoviesRepository
import ru.dashkevich.domain.repository.RoomRepository

val repositoryModule = module {

    single { MoviesRepository(get()) }
    single { DataStoreRepository(get()) }
    single { RoomRepository(get()) }

}


val domainModules = module { includes(repositoryModule) }