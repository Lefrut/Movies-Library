package ru.dashkevich.library

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.domain.repository.MoviesRepository
import ru.dashkevich.library.model.EventHandler
import ru.dashkevich.library.model.LibraryEvent
import ru.dashkevich.library.model.LibraryState
import ru.dashkevich.library.model.ScreenStatus

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

}