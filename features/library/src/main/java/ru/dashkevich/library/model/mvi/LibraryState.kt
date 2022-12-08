package ru.dashkevich.library.model.mvi

import ru.dashkevich.data.api.model.Movies
import ru.dashkevich.domain.model.PresentedMovies


data class LibraryState(
    val movies: PresentedMovies = PresentedMovies(),
    val screenStatus: ScreenStatus = ScreenStatus.Waiting,
    val errorMessage: String = ""
)

enum class ScreenStatus {
    Success, Error, Loading, Waiting
}
