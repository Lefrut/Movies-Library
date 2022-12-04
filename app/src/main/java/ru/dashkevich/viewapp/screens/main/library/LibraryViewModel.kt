package ru.dashkevich.viewapp.screens.main.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dashkevich.viewapp.common.EventHandler
import ru.dashkevich.viewapp.data.repository.MoviesRepository
import ru.dashkevich.viewapp.screens.main.library.model.LibraryEvent
import ru.dashkevich.viewapp.screens.main.library.model.LibraryState

class LibraryViewModel(private val moviesRepository: MoviesRepository)
    : ViewModel(), EventHandler<LibraryEvent> {

    private val _libraryState: MutableLiveData<LibraryState> = MutableLiveData(LibraryState())
    val libraryState: LiveData<LibraryState> = _libraryState


    override fun processingEvent(event: LibraryEvent) {
        when (event) {
            else -> {}
        }
    }



}