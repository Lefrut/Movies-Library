package ru.dashkevich.viewapp.screens.main.library

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.viewapp.common.EventHandler
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent
import ru.dashkevich.viewapp.screens.main.library.model.LibraryState

class LibraryViewModel(private val moviesRepository: MoviesRepository) : ViewModel(),
    EventHandler<LibraryEvent> {

    private val _libraryState: MutableLiveData<LibraryState> = MutableLiveData(LibraryState())
    val libraryState: LiveData<LibraryState> = _libraryState


    override fun processingEvent(event: LibraryEvent) {
        when (event) {
            LibraryEvent.RequestResultClicked -> {
                requestResultClicked()
            }
            else -> {}
        }
    }


    private fun requestResultClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getTopFilms().collect { movies ->
                _libraryState.postValue(_libraryState.value?.copy(movies = movies))
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