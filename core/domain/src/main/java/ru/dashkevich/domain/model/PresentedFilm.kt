package ru.dashkevich.domain.model


data class PresentedMovies(
    val films: List<PresentedFilm> = emptyList(),
    val pageCount: Int = 0
)


data class PresentedFilm(
    val title: String,
    val posterUrl: String,
    val rating: String,
    val saved: Boolean = false
)