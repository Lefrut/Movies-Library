package ru.dashkevich.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dashkevich.data.database.dao.SavedFilmDao
import ru.dashkevich.data.database.entity.SavedFilm

@Database(entities = [SavedFilm::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun savedFilmDao() : SavedFilmDao

}