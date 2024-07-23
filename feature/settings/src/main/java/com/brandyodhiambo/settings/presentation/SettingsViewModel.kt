package com.brandyodhiambo.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.ReminderMode
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.domain.repository.TimeFormateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val timeFormateRepository: TimeFormateRepository
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

    private val _selectedReminderMode =  mutableStateOf("")
    val selectedReminderMode: State<String> = _selectedReminderMode
    fun onReminderSelected(value:String){
        _selectedReminderMode.value = value
    }

    private val _selectedCustomDays = MutableStateFlow<MutableList<String>>(mutableListOf())
    val selectedCustomDays: StateFlow<MutableList<String>> = _selectedCustomDays


    fun onSelectedDayChangeState(item: String, isChecked: Boolean) {
        val updatedList = _selectedCustomDays.value.toMutableList()
        if (isChecked) {
            updatedList.add(item)
        } else {
            updatedList.remove(item)
        }
        _selectedCustomDays.value = updatedList
    }


    private val timeFormateFromDb  = timeFormateRepository.getTimeFormate()

    fun insertTimeFormate(timeFormate: TimeFormate){
        viewModelScope.launch {
            if(timeFormateFromDb.value != null){
                timeFormateRepository.deleteAllTimeFormate()
                timeFormateRepository.insertTimeFormate(timeFormate)
            } else{
                timeFormateRepository.insertTimeFormate(timeFormate)
            }
        }
    }

    // reminder Mode
    val reminderModeFromDb = timeFormateRepository.getReminderMode()

    fun insertReminderMode(reminderMode: ReminderMode){
        viewModelScope.launch {
            timeFormateRepository.insertReminderMode(reminderMode)
        }
    }

    fun deleteReminderMode(reminderMode: ReminderMode){
       viewModelScope.launch {
           timeFormateRepository.deleteReminderMode(reminderMode)
       }
    }

    fun deleteAllReminderMode(){
        viewModelScope.launch {
            timeFormateRepository.deleteAllReminderMode()
        }
    }

}