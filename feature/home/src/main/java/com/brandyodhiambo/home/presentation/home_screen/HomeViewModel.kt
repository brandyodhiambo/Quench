package com.brandyodhiambo.home.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
) : ViewModel() {

    private val _idealWaterIntake = mutableStateOf("0")
    var idealWaterIntakeValue: State<String> = _idealWaterIntake
    fun setIdealWaterIntakeValue(value: String) {
        _idealWaterIntake.value = value
    }

    private val _idealWaterForm = mutableStateOf("ml")
    var idealWaterForm: State<String> = _idealWaterForm
    fun setIdealWaterForm(value: String) {
        _idealWaterForm.value = value
    }

    private val _eventsFlow = MutableSharedFlow<UiEvents>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    val idealWaterIntakeFromDb = idealWaterIntakeRepository.getIdealWaterIntake()

    fun insertIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        viewModelScope.launch {
            idealWaterIntakeRepository.insertIdealWaterIntake(idealWaterIntake)

        }
    }

    fun updateIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        viewModelScope.launch {
            idealWaterIntakeRepository.updateIdealWaterIntake(idealWaterIntake)
        }
    }

    fun deleteIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        viewModelScope.launch {
            idealWaterIntakeRepository.deleteIdealWaterIntake(idealWaterIntake)
        }
    }

    fun deleteAllIdealWaterIntakes() {
        viewModelScope.launch {
            idealWaterIntakeRepository.deleteAllIdealWaterIntakes()
        }
    }

}