package ru.dashkevich.library

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.model.PresentedMovies
import ru.dashkevich.domain.repository.MoviesRepository
import ru.dashkevich.domain.repository.RoomRepository
import ru.dashkevich.library.model.mvi.EventHandler
import ru.dashkevich.library.model.mvi.LibraryEvent
import ru.dashkevich.library.model.mvi.LibraryState
import ru.dashkevich.library.model.mvi.ScreenStatus

class LibraryViewModel(
    private val moviesRepository: MoviesRepository,
    private val roomRepository: RoomRepository
) : ViewModel(),
    EventHandler<LibraryEvent> {

    private val _viewState: MutableLiveData<LibraryState> = MutableLiveData(LibraryState())
    val viewState: LiveData<LibraryState> = _viewState


    override fun processingEvent(event: LibraryEvent) {
        when (event) {
            LibraryEvent.RequestResultClicked -> {
                requestResultClicked()
            }
            LibraryEvent.LeavingScreen -> {
                leavingScreen()
            }
            is LibraryEvent.SavedFilmClicked -> {
                savedFilmClicked(event.saved, event.indexFilm)
            }
        }
    }

    private fun savedFilmClicked(savedState: Boolean, indexFilm: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val pageCount = _viewState.value?.movies?.pageCount ?: 1
            val films = _viewState.value?.movies?.films?.toMutableList()
            val unmodifiedFilm = films?.get(indexFilm)
            if (unmodifiedFilm != null) {
                val modifiedFilm = with(unmodifiedFilm) {
                    PresentedFilm(title, posterUrl, rating, savedState)
                }
                films[indexFilm] = modifiedFilm
                roomRepository.addSavedFilms(listOf(modifiedFilm))
                _viewState.postValue(
                    viewState.value?.copy(
                        movies = PresentedMovies(
                            films = films,
                            pageCount = pageCount
                        )
                    )
                )
            }
        }

    }

    private fun leavingScreen() {
        _viewState.postValue(LibraryState())
    }


    private fun requestResultClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getTopFilms().onSuccess { movies ->
                _viewState.postValue(
                    viewState.value?.copy(
                        movies = movies,
                        screenStatus = ScreenStatus.Success
                    )
                )
            }.onFailure {
                _viewState.postValue(
                    viewState.value?.copy(
                        screenStatus = ScreenStatus.Error,
                        errorMessage = it.message.toString()
                    )
                )
            }
        }
    }

}