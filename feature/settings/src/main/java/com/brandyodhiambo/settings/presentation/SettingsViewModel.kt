package com.brandyodhiambo.settings.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.domain.repository.TimeFormateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _openWaterUnitDialog =  mutableStateOf(false)
    val openWaterUnitDialog: State<Boolean> = _openWaterUnitDialog
    fun setOpenWaterUnitDialog(value:Boolean){
        _openWaterUnitDialog.value = value
    }

    private val _openWeightUnitDialog =  mutableStateOf(false)
    val openWeightUnitDialog: State<Boolean> = _openWeightUnitDialog
    fun setOpenWeightUnitDialog(value:Boolean){
        _openWeightUnitDialog.value = value
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

    val timeFormateFromDb  = timeFormateRepository.getTimeFormate()

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

}