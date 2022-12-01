package ru.dashkevich.viewapp.screens.splash

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.util.log.logE

data class ReadyNextScreen(var progress: Int = 0, val dataTake: Boolean = false)

class SplashViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val _readyNextScreen: MutableLiveData<ReadyNextScreen> = MutableLiveData(ReadyNextScreen())
    val readyNextScreen: LiveData<ReadyNextScreen> = _readyNextScreen

    private val _rememberUser: MutableLiveData<Boolean> = MutableLiveData(false)
    val rememberUser: LiveData<Boolean> = _rememberUser



    init {
        getOptionRememberUser()
        viewModelScope.launch(Dispatchers.Default) {
            startProgressBar()
        }
    }
    private fun getOptionRememberUser() {
        viewModelScope.launch {
            dataStoreRepository.getOptionRememberUser().onSuccess { data ->
                data.collect {
                    if (it != null) _rememberUser.postValue(it)
                    else _rememberUser.postValue(false)
                }
            }.onFailure { error ->
                _rememberUser.postValue(false)
                logE("DataStore", "getOption: ${error.localizedMessage}")
            }
            _readyNextScreen.postValue(_readyNextScreen.value?.copy(dataTake = true))
        }
    }


    private suspend fun startProgressBar() {
        readyNextScreen.value?.apply {
            while (progress != 99) {
                delay(25)
                _readyNextScreen.postValue(_readyNextScreen.value?.copy(progress = addProgress(progress)))
            }
        }
    }

    private fun addProgress(value: Int) = (value + 1)

    companion object {
        @Suppress("UNCHECKED_CAST")
        class Factory(private val repository: DataStoreRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                    return SplashViewModel(repository) as T
                }
                throw IllegalAccessException("SplashViewModel class not found!")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}