package ru.dashkevich.authorization.screens.login

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.dashkevich.authorization.screens.login.model.LoginState
import ru.dashkevich.domain.repository.DataStoreRepository
import ru.dashkevich.utility.constants.REMEMBER_USER_KEY
import ru.dashkevich.utility.log.logE
import ru.dashkevich.utility.log.logI


class LoginViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

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