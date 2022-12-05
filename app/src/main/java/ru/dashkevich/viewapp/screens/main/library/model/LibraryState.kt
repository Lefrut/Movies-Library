package ru.dashkevich.viewapp.screens.main.library.model

import ru.dashkevich.viewapp.data.api.movies.model.Movies

data class LibraryState(
    val movies: Movies = Movies(),
    val screenStatus: ScreenStatus = ScreenStatus.Waiting,
    val errorMessage: String = ""
)

enum class ScreenStatus {
    Success, Error, Loading, Waiting
}
