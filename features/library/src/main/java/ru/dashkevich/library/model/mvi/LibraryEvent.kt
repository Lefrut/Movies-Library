package ru.dashkevich.library.model.mvi

sealed class LibraryEvent{
    object RequestResultClicked : LibraryEvent()
    object LeavingScreen : LibraryEvent()
    class SavedFilmClicked(val saved: Boolean, val indexFilm: Int): LibraryEvent()
}
