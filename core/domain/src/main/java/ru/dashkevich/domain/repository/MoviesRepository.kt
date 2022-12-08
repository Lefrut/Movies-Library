package ru.dashkevich.domain.repository

import ru.dashkevich.data.api.MoviesService
import ru.dashkevich.data.api.model.Movies
import ru.dashkevich.data.api.model.TypesTopFilms

class MoviesRepository(private val moviesApi: MoviesService) {

    suspend fun getTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_100_POPULAR_FILMS,
        numberPage: Int = 4
    ): Result<Movies> {
        val topMovies = moviesApi.getTopFilms(type = type.name, page = numberPage)
        return runCatching{ topMovies }
    }
}