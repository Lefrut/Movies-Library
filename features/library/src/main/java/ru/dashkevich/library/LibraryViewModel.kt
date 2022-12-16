package ru.dashkevich.library

import android.annotation.SuppressLint
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.dashkevich.domain.model.PresentedFilm
import ru.dashkevich.domain.model.PresentedMovies
import ru.dashkevich.domain.repository.MoviesRepository
import ru.dashkevich.domain.repository.RoomRepository
import ru.dashkevich.library.model.mvi.EventHandler
import ru.dashkevich.library.model.mvi.LibraryEvent
import ru.dashkevich.library.model.mvi.LibraryState
import ru.dashkevich.library.model.mvi.ScreenStatus

@SuppressLint("CheckResult")
@OptIn(ExperimentalCoroutinesApi::class)
class LibraryViewModel(
    private val moviesRepository: MoviesRepository,
    private val roomRepository: RoomRepository
) : ViewModel(),
    EventHandler<LibraryEvent> {

    private val _viewState: MutableLiveData<LibraryState> = MutableLiveData(LibraryState())
    val viewState: LiveData<LibraryState> = _viewState


//    init {
//        moviesRepository.observeTopFilms()
//            .onEach {
//                _viewState.postValue(viewState.value?.copy(pagingMoviesData = it))
//            }.cachedIn(viewModelScope)
//    }

    @OptIn(FlowPreview::class)
    val filmsFlow: Flow<PagingData<PresentedFilm>> = _viewState.asFlow()
        .flatMapLatest {
            moviesRepository.observeTopFilms()
        }.cachedIn(viewModelScope)

    val noFilmsFlow: StateFlow<PagingData<PresentedFilm>> =
        moviesRepository.observeTopFilms()
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
            //.cachedIn(viewModelScope)


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
            is LibraryEvent.DataCame -> {}
        }
    }

    private fun savedFilmClicked(savedState: Boolean, indexFilm: Int) {
        //TODO("Create page number")
        viewModelScope.launch(Dispatchers.IO) {
            //val pageCount = _viewState.value?.movies?.pageCount ?: 1
            //val films = _viewState.value?.movies?.films?.toMutableList()
            moviesRepository.getTopFilms(numberPage = 1).onSuccess { movie ->
                val films = movie.films.toMutableList()
                val unmodifiedFilm = films[indexFilm]
                val modifiedFilm = with(unmodifiedFilm) {
                    PresentedFilm(id, title, posterUrl, rating, savedState)
                }
                films[indexFilm] = modifiedFilm
                if (savedState) roomRepository.addSavedFilms(listOf(modifiedFilm))
                else roomRepository.deleteSavedFilms(listOf(modifiedFilm))
                _viewState.postValue(
                    viewState.value?.copy(
                        movies = PresentedMovies(
                            films = films,
                            pageCount = 1
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
            _viewState.postValue(
                viewState.value?.copy(screenStatus = ScreenStatus.Loading)
            )
            delay(100)
            moviesRepository.getTopFilms().onSuccess { movies ->
                val presentedFilms = mergingSavedFilms(movies)

                _viewState.postValue(
                    viewState.value?.copy(
                        movies = PresentedMovies(
                            films = presentedFilms,
                            pageCount = movies.pageCount
                        ),
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

    private suspend fun mergingSavedFilms(movies: PresentedMovies)
            : MutableList<PresentedFilm> {
        val presentedFilms: MutableList<PresentedFilm> = mutableListOf()
        movies.films.forEach { films ->
            var saved = false
            roomRepository.getSavedFilms().forEach { savedFilms ->
                if (films.id == savedFilms.id) {
                    saved = true
                }
            }
            presentedFilms += films.copy(saved = saved)
        }
        return presentedFilms
    }

}