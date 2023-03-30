package com.brandyodhiambo.home.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.SelectedDrinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository,
    private val selectedDrinkRepository: SelectedDrinkRepository
) : ViewModel() {

/*
* ideal water intake value and functions
* */
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

/*
* Goal Water intake values and fuctions
* */
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

    /*
    * Selected drink values and functions
    * */
    private val _size = mutableStateOf("0")
    var size: State<String> = _size
    fun setSize(value: String) {
        _size.value = value
    }

    private val _selectedtime = mutableStateOf("12:00 Am")
    var selectedTime: State<String> = _selectedtime
    fun setSelectedTime(value: String) {
        _selectedtime.value = value
    }

    private val _selectedIcon = mutableStateOf(0)
    var selectedIcon: State<Int> = _selectedIcon
    fun setSelectedIcon(value: Int) {
        _selectedIcon.value = value
    }

    val selectedDrinkFromDB = selectedDrinkRepository.getSelectedDrink()

    fun insertSelectedDrink(selectedDrink: SelectedDrink) {
        viewModelScope.launch {
            selectedDrinkRepository.insertSelectedDrink(selectedDrink)
        }
    }



}