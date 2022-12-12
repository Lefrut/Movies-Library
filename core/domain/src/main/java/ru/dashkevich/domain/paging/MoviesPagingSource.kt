package ru.dashkevich.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.dashkevich.data.api.MoviesService
import ru.dashkevich.data.api.model.Film
import ru.dashkevich.domain.model.PresentedFilm

class MoviesPagingSource(
    private val api: MoviesService,
    private val typeTopFilm: String
) : PagingSource<Int, PresentedFilm>() {

    override fun getRefreshKey(state: PagingState<Int, PresentedFilm>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PresentedFilm> {
        return try {
            val key = params.key ?: 1
            val movies = api.getTopFilms(type = typeTopFilm, page = key)
            LoadResult.Page(
                data = movies.films.map {
                    PresentedFilm(
                        id = it.filmId,
                        title = it.nameRu,
                        posterUrl = it.posterUrl,
                        rating = it.rating,
                        saved = false
                    )
                },
                prevKey = if (key == 1) null else key - 1,
                nextKey = key + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }


}