package ru.dashkevich.viewapp.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val mutableProgress: MutableLiveData<Int> = MutableLiveData(0)
    val progress: LiveData<Int> = mutableProgress

    init {
        viewModelScope.launch(Dispatchers.Default) {
            startProgressBar()
        }
    }

    private suspend fun startProgressBar() {
        while (mutableProgress.value != 100) {
            delay(25)
            val newValue = progress.value?.let { addProgress(it) } ?: -1
            mutableProgress.postValue(newValue)
        }
    }

    private fun addProgress(value: Int) = (value + 1)

    fun clearData() = mutableProgress.postValue(0)

    override fun onCleared() {
        super.onCleared()
    }
}