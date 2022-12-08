package ru.dashkevich.data.api.model

data class Movies(
    val films: List<Film> = emptyList(),
    val pagesCount: Int = 0
)