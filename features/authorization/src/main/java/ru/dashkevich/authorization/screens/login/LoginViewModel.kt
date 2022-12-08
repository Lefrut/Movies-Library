package ru.dashkevich.authorization.screens.login

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dashkevich.domain.repository.DataStoreRepository
import ru.dashkevich.utility.constants.REMEMBER_USER_KEY
import ru.dashkevich.utility.log.logE
import ru.dashkevich.utility.log.logI


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