package ru.dashkevich.library.model

sealed class LibraryEvent{
    object RequestResultClicked : LibraryEvent()
    object LeavingScreen : LibraryEvent()
}
