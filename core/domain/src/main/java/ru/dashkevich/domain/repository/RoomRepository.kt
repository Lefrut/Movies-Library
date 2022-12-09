package ru.dashkevich.domain.repository

import ru.dashkevich.data.database.AppDatabase
import ru.dashkevich.data.database.entity.SavedFilm
import ru.dashkevich.domain.model.PresentedFilm

class RoomRepository(private val db: AppDatabase) {

    private val savedFilmDao = db.savedFilmDao()


    suspend fun deleteSavedFilms(presentedFilms: List<PresentedFilm>) {
        val savedFilms = presentedFilms.map {
            SavedFilm(
                id = it.id,
                title = it.title,
                posterUrl = it.posterUrl,
                rating = it.rating
            )
        }
        savedFilmDao.reduceSavedFilms(savedFilms)
    }

    suspend fun addSavedFilms(presentedFilms: List<PresentedFilm>) {
        val savedFilms = presentedFilms.map {
            SavedFilm(
                id = it.id,
                title = it.title,
                posterUrl = it.posterUrl,
                rating = it.rating
            )
        }
        savedFilmDao.addSavedFilms(savedFilms)
    }

    suspend fun getSavedFilms(): List<PresentedFilm> {
        val films = savedFilmDao.getSavedFilms()
            .map {
                PresentedFilm(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.posterUrl,
                    rating = it.rating,
                    saved = true
                )
            }
        return films
    }

}