package ru.dashkevich.viewapp.data.api.movies.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dashkevich.viewapp.data.api.movies.MoviesService


const val MOVIE_URL = "https://kinopoiskapiunofficial.tech/api/"


val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val moviesApi: MoviesService by lazy {
    retrofit.create(MoviesService::class.java)
}