package ru.dashkevich.viewapp.data.api.movies.model

data class Movies(
    val films: List<Film> = emptyList(),
    val pagesCount: Int = 0
)