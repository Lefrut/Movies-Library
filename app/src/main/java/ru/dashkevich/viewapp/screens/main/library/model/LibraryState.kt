package ru.dashkevich.viewapp.screens.main.library.model

import ru.dashkevich.viewapp.data.api.movies.model.Movies

data class LibraryState(
    val films: Movies = Movies()
)