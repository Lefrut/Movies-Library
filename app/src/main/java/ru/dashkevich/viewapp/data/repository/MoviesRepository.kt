package ru.dashkevich.viewapp.data.repository

import ru.dashkevich.viewapp.data.api.movies.MoviesService
import ru.dashkevich.viewapp.data.api.movies.model.Movies
import ru.dashkevich.viewapp.data.api.movies.model.TypesTopFilms

class MoviesRepository(private val moviesApi: MoviesService) {

    suspend fun getTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_250_BEST_FILMS,
        numberPage: Int = 1
    ): Result<Movies> {
        val topMovies = moviesApi.getTopFilms(type = type.name, page = numberPage)
        return runCatching{ topMovies }
    }
}