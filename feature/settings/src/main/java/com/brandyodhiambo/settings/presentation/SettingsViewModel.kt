package com.brandyodhiambo.settings.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.ReminderMode
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.domain.repository.SettingRepository
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) :ViewModel() {
    private val _openTimeDialog =  mutableStateOf(false)
    val openTimeDialog: State<Boolean> = _openTimeDialog
    fun setOpenTimeDialog(value:Boolean){
        _openTimeDialog.value = value
    }

    private val _repeatModeDialog =  mutableStateOf(false)
    val repeatModeDialog: State<Boolean> = _repeatModeDialog
    fun setRepeatModeDialog(value:Boolean){
        _repeatModeDialog.value = value
    }

    private val _selectedTimeFormate =  mutableStateOf("12")
    val selectedTimeFormate: State<String> = _selectedTimeFormate
    fun onTimeFormatSelected(value:String){
        _selectedTimeFormate.value = value
    }

    private val _selectedMode =  mutableStateOf("Once")
    val selectedMode: State<String> = _selectedMode
    fun onSelectedMode(value:String){
        _selectedMode.value = value
    }

    private val _selectedCustomDays = MutableStateFlow<List<String>>(emptyList())
    val selectedCustomDays: StateFlow<List<String>> = _selectedCustomDays
    fun onSelectedDayChangeState(item: List<String>) {
        _selectedCustomDays.value = item
    }

    private val _isVibrated = mutableStateOf(false)
    val isVibrated:State<Boolean> = _isVibrated
    fun setIsVibrated(value:Boolean){
        _isVibrated.value = value
    }

    private val _isDeleted = mutableStateOf(false)
    val isDeleted:State<Boolean> = _isDeleted
    fun setIsDeleted(value:Boolean){
        _isDeleted.value = value
    }

    private val _timePickerValue = mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM))
    val timePickerValue: MutableState<Hours> = _timePickerValue

    private val _hour = mutableStateOf(0)
    var hour: State<Int> = _hour

    private val _minutes = mutableStateOf(0)
    var minutes: State<Int> = _minutes

    private val _ampm = mutableStateOf("")
    var ampm: State<String> = _ampm
    fun onTimeSelected(hours: Int, minutes: Int, amPm: String) {
        _hour.value = hours
        _minutes.value = minutes
        _ampm.value = amPm
    }


    private val timeFormateFromDb  = settingRepository.getTimeFormate()

    fun insertTimeFormate(timeFormate: TimeFormate){
        viewModelScope.launch {
            if(timeFormateFromDb.value != null){
                settingRepository.deleteAllTimeFormate()
                settingRepository.insertTimeFormate(timeFormate)
            } else{
                settingRepository.insertTimeFormate(timeFormate)
            }
        }
    }

    val reminderModeFromDb = settingRepository.getReminderMode()

    fun insertReminderMode(reminderMode: ReminderMode){
        viewModelScope.launch {
            settingRepository.insertReminderMode(reminderMode)
        }
    }

    fun deleteReminderMode(reminderMode: ReminderMode){
       viewModelScope.launch {
           settingRepository.deleteReminderMode(reminderMode)
       }
    }

    fun deleteAllReminderMode(){
        viewModelScope.launch {
            settingRepository.deleteAllReminderMode()
        }
    }

}