package ru.dashkevich.domain.repository

import ru.dashkevich.data.api.MoviesService
import ru.dashkevich.data.api.model.TypesTopFilms
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.model.PresentedMovies

class MoviesRepository(private val moviesApi: MoviesService) {

    suspend fun getTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_100_POPULAR_FILMS,
        numberPage: Int = 4
    ): Result<PresentedMovies> {
        val topMovies = moviesApi.getTopFilms(type = type.name, page = numberPage)
        return runCatching { PresentedMovies(
            films = topMovies.films.map {
                PresentedFilm(
                    id = it.filmId,
                    title = it.nameRu,
                    posterUrl = it.posterUrl,
                    rating = it.rating
                )},
            pageCount = topMovies.pagesCount)
        }
    }
}
