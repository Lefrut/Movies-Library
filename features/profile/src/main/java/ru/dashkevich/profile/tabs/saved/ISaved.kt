package ru.dashkevich.profile.tabs.saved

import ru.dashkevich.library.adapter.MoviesAdapter

interface PresenterSavedI{
    suspend fun deleteSavedMovies(saved: Boolean, index: Int)
    suspend fun getSavedMovies()
}

interface ViewSavedI{
    fun showSavedMovies(adapter: MoviesAdapter)
}