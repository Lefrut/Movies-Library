package ru.dashkevich.library.model

import ru.dashkevich.data.api.model.Movies


data class LibraryState(
    val movies: Movies = Movies(),
    val screenStatus: ScreenStatus = ScreenStatus.Waiting,
    val errorMessage: String = ""
)

enum class ScreenStatus {
    Success, Error, Loading, Waiting
}
