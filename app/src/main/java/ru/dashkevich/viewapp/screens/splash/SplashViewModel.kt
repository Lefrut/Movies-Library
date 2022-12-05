package ru.dashkevich.viewapp.screens.splash

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dashkevich.viewapp.data.repository.DataStoreRepository
import ru.dashkevich.viewapp.util.log.logE
import ru.dashkevich.viewapp.util.log.logI

data class ReadyNextScreen(
    var progress: Int = 0,
    val dataTake: Boolean = false
)

class SplashViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {


    private val _readyNextScreen: MutableLiveData<ReadyNextScreen> =
        MutableLiveData(ReadyNextScreen())
    val readyNextScreen: LiveData<ReadyNextScreen> = _readyNextScreen

    private val _rememberUser: MutableLiveData<Boolean> = MutableLiveData(false)
    val rememberUser: LiveData<Boolean> = _rememberUser


    init {
        splashScreenLoading()
    }

    private fun splashScreenLoading() {
        viewModelScope.launch {
            dataStoreRepository.getOptionRememberUser().onSuccess { data ->
                data.collect {
                    if (it != null) _rememberUser.postValue(it)
                    else _rememberUser.postValue(false)
                    updateReadyNextScreen(_readyNextScreen.value?.copy(dataTake = true))
                    startProgressBar()
                }
            }.onFailure { error ->
                _rememberUser.postValue(false)
                logE("DataStore", "getOption: ${error.localizedMessage}")
            }
        }
    }

    @Synchronized
    fun updateReadyNextScreen(readyNextScreen: ReadyNextScreen?) {
        _readyNextScreen.postValue(readyNextScreen!!)
    }

    private suspend fun startProgressBar() {
        while (readyNextScreen.value?.progress != 100) {
            delay(20)
            updateReadyNextScreen(
                _readyNextScreen.value?.copy(
                    progress = readyNextScreen.value?.progress?.plus(1) ?: 101
                )
            )
        }
    }


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