package ru.dashkevich.viewapp.screens.main.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.dashkevich.viewapp.common.EventHandler
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.screens.login.LoginViewModel
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent
import ru.dashkevich.viewapp.screens.main.library.model.LibraryState
import ru.dashkevich.viewapp.screens.splash.SplashViewModel

class LibraryViewModel(private val moviesRepository: MoviesRepository) : ViewModel(),
    EventHandler<LibraryEvent> {

    private val _libraryState: MutableLiveData<LibraryState> = MutableLiveData(LibraryState())
    val libraryState: LiveData<LibraryState> = _libraryState


    override fun processingEvent(event: LibraryEvent) {
        when (event) {
            else -> {}
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