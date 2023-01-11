package ru.dashkevich.profile.tabs.saved.presenter

import android.widget.ProgressBar
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.repository.RoomRepository
import ru.dashkevich.library.adapter.MoviesAdapter
import ru.dashkevich.profile.tabs.saved.PresenterSavedI
import ru.dashkevich.profile.tabs.saved.ViewSavedI

class SavedPresenter(
    private val roomRepository: RoomRepository
    ) : PresenterSavedI, ViewSavedI {

    private var savedFilms: MutableList<PresentedFilm> = mutableListOf()
    private lateinit var presenterAsyncScope: CoroutineScope

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

    fun updateGetUI(adapter: MoviesAdapter, progressBar: ProgressBar) {
        presenterAsyncScope = CoroutineScope(Dispatchers.Default)
        presenterAsyncScope.launch {
            visibleLoadingBar(true, progressBar)
            getSavedMovies()
            withContext(Dispatchers.Main) {
                delay(300)
                showSavedMovies(adapter)
                visibleLoadingBar(false, progressBar)
            }
        }
    }

    fun updateDeleteUI(saved: Boolean, index: Int, adapter: MoviesAdapter) {
        presenterAsyncScope.launch(Dispatchers.IO) {
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

    fun stopWorking(){
        presenterAsyncScope.cancel()
    }

    private fun visibleLoadingBar(isVisible: Boolean, progressBar: ProgressBar){
        progressBar.isVisible = isVisible
    }


}