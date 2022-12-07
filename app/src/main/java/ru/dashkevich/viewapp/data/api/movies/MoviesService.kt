package ru.dashkevich.viewapp.data.api.movies


import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.dashkevich.viewapp.data.api.movies.model.Movies


interface MoviesService {

    @Headers(
        "accept: application/json",
        "X-API-KEY: 9ab8563f-6aae-42ff-9781-0dda57cae1ff"
    )
    @GET("v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Movies


}