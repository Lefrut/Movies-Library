package ru.dashkevich.viewapp.screens.login

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.util.log.logE
import ru.dashkevich.viewapp.util.log.logI

class LoginViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    fun addOptionRememberUser(rememberUser: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.addOptionRememberUser(rememberUser).onSuccess { preference ->
                logI("DataStore", "add Option: ${preference[REMEMBER_USER_KEY]}")
            }.onFailure { error ->
                logE("DataStore", "add Option: ${error.localizedMessage}")
            }
        }
    }

}