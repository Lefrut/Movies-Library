package ru.dashkevich.viewapp.screens.main.library.model

sealed class LibraryEvent{
    object RequestResultClicked : LibraryEvent()
    object LeavingScreen : LibraryEvent()
}
