package ru.dashkevich.viewapp.data.repository

import kotlinx.coroutines.flow.Flow
import ru.dashkevich.viewapp.data.api.movies.MoviesService
import ru.dashkevich.viewapp.data.api.movies.model.Movies
import ru.dashkevich.viewapp.data.api.movies.model.TypesTopFilms

class MoviesRepository(private val moviesApi: MoviesService) {

    fun getTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_250_BEST_FILMS,
        numberPage: Int = 1
    ): Flow<Movies> = moviesApi.getTopFilms(type = type.name, page = numberPage)

}