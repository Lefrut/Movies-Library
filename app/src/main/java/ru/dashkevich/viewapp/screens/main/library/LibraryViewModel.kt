package ru.dashkevich.viewapp.screens.main.library

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.viewapp.common.EventHandler
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent
import ru.dashkevich.viewapp.screens.main.library.model.LibraryState
import ru.dashkevich.viewapp.screens.main.library.model.ScreenStatus

class LibraryViewModel(private val moviesRepository: MoviesRepository) : ViewModel(),
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


    companion object {

        @Suppress("UNCHECKED_CAST")
        class Factory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
                    return LibraryViewModel(moviesRepository) as T
                }
                throw IllegalAccessException("LibraryViewModel class not found!")
            }

        }

    }

}