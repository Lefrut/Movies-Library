package ru.dashkevich.viewapp.util.constants

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.dashkevich.viewapp.util.log.logE


const val USER_LOGIN = "USER_LOGIN"
const val USER_PASSWORD = "USER_PASSWORD"
const val USER_DATA = "USER_DATA"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings",
    corruptionHandler = ReplaceFileCorruptionHandler { error ->
        logE("DataStore", "error create dataStore: ${error.message}")
        emptyPreferences()
    },
    scope = CoroutineScope(Dispatchers.IO)
)