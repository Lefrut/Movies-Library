package ru.dashkevich.domain.repository

import ru.dashkevich.data.database.AppDatabase
import ru.dashkevich.data.database.entity.SavedFilm
import ru.dashkevich.domain.model.PresentedFilm

class RoomRepository(private val db: AppDatabase) {

    private val savedFilmDao = db.savedFilmDao()

    suspend fun addSavedFilms(films: List<PresentedFilm>) {
        val savedFilms = films.map {
            SavedFilm(
                id = 0,
                title = it.title,
                posterUrl = it.posterUrl,
                rating = it.rating
            )
        }
        savedFilmDao.addSavedFilms(savedFilms)

    }

}