package com.example.hm_7_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var elapsedTimeSeconds: Long = 0

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    private var timerJob: Job? = null

    fun startTimer(){

        val coroutine = viewModelScope.launch {
            timerJob?.cancel()

            timerJob = CoroutineScope(Dispatchers.Main).launch {
                while (isActive) {
                    delay(1000)
                    elapsedTimeSeconds++
                    _elapsedTime.postValue(elapsedTimeSeconds)
                }
            }
        }

    }

    fun stopTimer(){
        timerJob?.cancel()
    }

    fun resetTimer() {
        timerJob?.cancel()
        elapsedTimeSeconds = 0
        _elapsedTime.postValue(elapsedTimeSeconds)
    }
}