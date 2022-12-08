package ru.dashkevich.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map
import ru.dashkevich.utility.constants.REMEMBER_USER_KEY


class DataStoreRepository(private val dataStore: DataStore<Preferences>) {

    suspend fun addOptionRememberUser(rememberUser: Boolean) = runCatching {
        dataStore.updateData {
            it.toMutablePreferences().apply {
                set(REMEMBER_USER_KEY, rememberUser)
            }
        }
    }

    fun getOptionRememberUser() = runCatching {
        dataStore.data.map { value: Preferences -> value[REMEMBER_USER_KEY] }
    }

}