package ru.dashkevich.library.model.mvi

import androidx.paging.PagingData
import ru.dashkevich.domain.model.PresentedFilm

sealed class LibraryEvent{
    object RequestResultClicked : LibraryEvent()
    object LeavingScreen : LibraryEvent()
    class DataCame(val data: PagingData<PresentedFilm>) : LibraryEvent()

    class SavedFilmClicked(val saved: Boolean, val indexFilm: Int): LibraryEvent()
}
