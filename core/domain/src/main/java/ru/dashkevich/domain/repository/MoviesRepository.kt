package ru.dashkevich.domain.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import ru.dashkevich.data.api.MoviesService
import ru.dashkevich.data.api.model.TypesTopFilms
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.model.PresentedMovies
import ru.dashkevich.domain.paging.MoviesPagingSource

class MoviesRepository(private val moviesApi: MoviesService) {



    suspend fun getTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_AWAIT_FILMS,
        numberPage: Int = 1
    ): Result<PresentedMovies> {

        val topMovies = moviesApi.getTopFilms(type = type.name, page = numberPage)
        return runCatching {
            PresentedMovies(
                films = topMovies.films.map {
                    PresentedFilm(
                        id = it.filmId,
                        title = it.nameRu,
                        posterUrl = it.posterUrl,
                        rating = it.rating
                    )
                },
                pageCount = topMovies.pagesCount
            )
        }
    }

    fun observeTopFilms(
        type: TypesTopFilms = TypesTopFilms.TOP_250_BEST_FILMS
    ): Flow<PagingData<PresentedFilm>> {
        return Pager(PagingConfig(0)) {
            MoviesPagingSource(api = moviesApi, typeTopFilm = type.name)
        }.flow
    }
}

