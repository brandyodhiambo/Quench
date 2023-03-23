package com.brandyodhiambo.home.presentation.sleep_wake_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.common.domain.repository.SleepTimeRepository
import com.brandyodhiambo.common.domain.repository.WakeTimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepWakeViewModel @Inject constructor(
    private val sleepTimeRepository: SleepTimeRepository,
    private val wakeTimeRepository: WakeTimeRepository
):ViewModel() {

    private val _sleepSelectedTime = mutableStateOf(SleepTime(0, 0, ""))
    var sleepSelectedTime: MutableState<SleepTime> = _sleepSelectedTime
    fun onTimeSleepSelected(hours: Int, minutes: Int, amPm: String) {
        _sleepSelectedTime.value = SleepTime(hours, minutes, amPm)
    }

    private val _wakeSelectedTime = mutableStateOf(WakeTime(0, 0, ""))
    var wakeSelectedTime: MutableState<WakeTime> = _wakeSelectedTime
    fun onTimeWakeSelected(hours: Int, minutes: Int, amPm: String) {
        _wakeSelectedTime.value = WakeTime(hours, minutes, amPm)
    }

    val sleepTime = sleepTimeRepository.getSleepTime()

    fun insertSleepTime(sleepTime: SleepTime) {
        viewModelScope.launch {
            sleepTimeRepository.insertSleepTime(sleepTime)
        }
    }

    fun updateSleepTime(sleepTime: SleepTime) {
        viewModelScope.launch {
            sleepTimeRepository.updateSleepTime(sleepTime)
        }
    }

    fun deleteSleepTime(sleepTime: SleepTime) {
        viewModelScope.launch {
            sleepTimeRepository.deleteSleepTime(sleepTime)
        }
    }

    fun deleteAllSleepTimes() {
        viewModelScope.launch {
            sleepTimeRepository.dellAllSleepTimes()
        }
    }

    // Wake Time
    val wakeTime = wakeTimeRepository.getWakeTime()

    fun insertWakeTime(wakeTime: WakeTime) {
        viewModelScope.launch {
            wakeTimeRepository.insertWakeTime(wakeTime)
        }
    }

    fun updateWakeTime(wakeTime: WakeTime) {
        viewModelScope.launch {
            wakeTimeRepository.updateWakeTime(wakeTime)
        }
    }

    fun deleteWakeTime(wakeTime: WakeTime) {
        viewModelScope.launch {
            wakeTimeRepository.deleteWakeTime(wakeTime)
        }
    }

    fun deleteAllWakeTimes() {
        viewModelScope.launch {
            wakeTimeRepository.dellAllWakeTimes()
        }
    }



}