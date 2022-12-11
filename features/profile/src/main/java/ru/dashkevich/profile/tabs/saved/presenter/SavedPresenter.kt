package ru.dashkevich.profile.tabs.saved.presenter

import kotlinx.coroutines.*
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.repository.RoomRepository
import ru.dashkevich.library.adapter.MoviesAdapter
import ru.dashkevich.profile.tabs.saved.PresenterSavedI
import ru.dashkevich.profile.tabs.saved.ViewSavedI

class SavedPresenter(private val roomRepository: RoomRepository) : PresenterSavedI, ViewSavedI {

    private var savedFilms: MutableList<PresentedFilm> = mutableListOf()


    override suspend fun deleteSavedMovies(saved: Boolean, index: Int) {
        val presentedFilm = savedFilms[index].copy(saved = saved)
        savedFilms[index] = presentedFilm
        roomRepository.deleteSavedFilms(listOf(presentedFilm))
    }

    override suspend fun getSavedMovies() {
        savedFilms = roomRepository.getSavedFilms().toMutableList()
    }

    override fun showSavedMovies(adapter: MoviesAdapter) {
        adapter.setData(savedFilms)
    }

    fun updateGetUI(adapter: MoviesAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            getSavedMovies()
            withContext(Dispatchers.Main) {
                showSavedMovies(adapter)
            }
        }
    }

    fun updateDeleteUI(saved: Boolean, index: Int, adapter: MoviesAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteSavedMovies(saved, index)
            withContext(Dispatchers.Main) {
                showSavedMovies(adapter)
            }
            delay(200)
            getSavedMovies()
            withContext(Dispatchers.Main) {
                showSavedMovies(adapter)
            }
        }
    }


}