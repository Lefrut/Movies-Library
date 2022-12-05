package ru.dashkevich.viewapp.di


import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dashkevich.viewapp.data.api.movies.MoviesService
import ru.dashkevich.viewapp.data.api.movies.util.MOVIE_URL


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



}

val appModules = listOf(
    dataStoreModule, retrofitModule
)