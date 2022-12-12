package ru.dashkevich.library.model.mvi

import androidx.paging.PagingData
import ru.dashkevich.data.api.model.Film
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.model.PresentedMovies


data class LibraryState(
    val movies: PresentedMovies = PresentedMovies(),
    val screenStatus: ScreenStatus = ScreenStatus.Waiting,
    val errorMessage: String = "",
    val pagingMoviesData: PagingData<PresentedFilm> = PagingData.empty()
)

enum class ScreenStatus {
    Success, Error, Loading, Waiting, EmptyResult
}
