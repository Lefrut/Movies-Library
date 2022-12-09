package ru.dashkevich.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.dashkevich.data.database.entity.SavedFilm

@Dao
interface SavedFilmDao {

    @Query("SELECT * FROM saved_film")
    fun observeSavedFilms(): Flow<List<SavedFilm>>
    @Query("SELECT * FROM saved_film")
    suspend fun getSavedFilms(): List<SavedFilm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSavedFilms(savedFilm: List<SavedFilm>)

    @Delete
    suspend fun reduceSavedFilms(savedFilm: List<SavedFilm>)
}