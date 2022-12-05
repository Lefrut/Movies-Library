package ru.dashkevich.viewapp.data.api.movies


import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.dashkevich.viewapp.data.api.movies.model.Movies
import ru.dashkevich.viewapp.data.api.movies.model.TypesTopFilms


interface MoviesService {

    @Headers(
        "accept: application/json",
        "X-API-KEY: 9ab8563f-6aae-42ff-9781-0dda57cae1ff"
    )
    @GET("v2.2/films/top")
    fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Flow<Movies>


}