package com.brandyodhiambo.home.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository
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


    val idealWaterIntakeFromDb = idealWaterIntakeRepository.getIdealWaterIntake()

    fun insertIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        viewModelScope.launch {
            if(idealWaterIntakeFromDb.value != null) {
                idealWaterIntakeRepository.deleteAllIdealWaterIntakes()
                idealWaterIntakeRepository.insertIdealWaterIntake(idealWaterIntake)
            } else {
                idealWaterIntakeRepository.insertIdealWaterIntake(idealWaterIntake)
            }
        }
    }


    private val _goalWaterIntake = mutableStateOf("0")
    var goalWaterIntakeValue: State<String> = _goalWaterIntake
    fun setGoalWaterIntakeValue(value: String) {
        _goalWaterIntake.value = value
    }

    private val _goalWaterForm = mutableStateOf("ml")
    var goalWaterForm: State<String> = _goalWaterForm
    fun setGoalWaterForm(value: String) {
        _goalWaterForm.value = value
    }

    val goalWaterIntakeFromDb = goalWaterIntakeRepository.getGoalWaterIntake()

    fun insertGoalWaterIntake(goalWaterIntake: GoalWaterIntake) {
        viewModelScope.launch {
            if(goalWaterIntakeFromDb.value != null) {
                goalWaterIntakeRepository.deleteAllGoalWaterIntakes()
                goalWaterIntakeRepository.insertGoalWaterIntake(goalWaterIntake)
            } else {
                goalWaterIntakeRepository.insertGoalWaterIntake(goalWaterIntake)
            }
        }
    }

}