/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brandyodhiambo.home.presentation.sleepWakeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.common.domain.repository.SleepTimeRepository
import com.brandyodhiambo.common.domain.repository.WakeTimeRepository
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepWakeViewModel @Inject constructor(
    private val sleepTimeRepository: SleepTimeRepository,
    private val wakeTimeRepository: WakeTimeRepository
) : ViewModel() {

    private val _sleepTimePickerValue = mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM))
    val sleepTimePickerValue: MutableState<Hours> = _sleepTimePickerValue

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

    private val _wakeTimePickerValue = mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM))
    val wakeTimePickerValue: MutableState<Hours> = _wakeTimePickerValue

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
