package com.brandyodhiambo.home.presentation.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.common.domain.repository.ReminderTimeRepository
import com.brandyodhiambo.common.domain.repository.SelectedDrinkRepository
import com.chargemap.compose.numberpicker.AMPMHours
import com.chargemap.compose.numberpicker.Hours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository,
    private val selectedDrinkRepository: SelectedDrinkRepository,
    private val levelRepository: LevelRepository,
    private val reminderTimeRepository: ReminderTimeRepository,
) : ViewModel() {

    /*
    * ideal water intake value and functions
    * */
    private val _idealWaterIntake = mutableStateOf("500")
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
            if (idealWaterIntakeFromDb.value != null) {
                idealWaterIntakeRepository.deleteAllIdealWaterIntakes()
                idealWaterIntakeRepository.insertIdealWaterIntake(idealWaterIntake)
            } else {
                idealWaterIntakeRepository.insertIdealWaterIntake(idealWaterIntake)
            }
        }
    }

    /*
    * Goal Water intake values and functions
    * */
    private val _goalWaterIntake = mutableStateOf("2080")
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
            if (goalWaterIntakeFromDb.value != null) {
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

    fun deleteAllSelectedDrinks() {
        viewModelScope.launch {
            selectedDrinkRepository.deleteAllSelectedDrinks()
        }
    }

    fun deleteOneSelectedDrink(id: Int) {
        viewModelScope.launch {
            selectedDrinkRepository.deleteOneSelectedDrink(id)
        }
    }

    /*
    * Level values and functions
    * */

    private val _amountTaken = mutableStateOf(0f)
    var amountTaken: State<Float> = _amountTaken
    fun setAmountTaken(value: Float) {
        _amountTaken.value = value
    }

    private val _waterTaken = mutableStateOf(0)
    var waterTaken: State<Int> = _waterTaken
    fun setWaterTaken(value: Int) {
        _waterTaken.value = value
    }

    val levelFromDB = levelRepository.getLevel()

    fun insertLevel(level: Level) {
        viewModelScope.launch {
            levelRepository.insertLevel(level)
        }
    }

    fun deleteAllLevels() {
        viewModelScope.launch {
            levelRepository.deleteAllLevel()
        }
    }

    fun deleteOneLevel(level: Level) {
        viewModelScope.launch {
            levelRepository.deleteLevel(level)
        }
    }

    fun updateLevel(level: Level) {
        viewModelScope.launch {
            levelRepository.updateLevel(level)
        }
    }

    /*
    * Reminder Time
    * */
    private val _reminderTimePickerValue =
        mutableStateOf<Hours>(AMPMHours(0, 0, AMPMHours.DayTime.AM))
    val reminderTimePickerValue: MutableState<Hours> = _reminderTimePickerValue

    private val _reminderSelectedTime =
        mutableStateOf(ReminderTime(0, 0, "", false, false, emptyList()))
    var reminderSelectedTime: MutableState<ReminderTime> = _reminderSelectedTime
    fun onReminderTimeSelected(
        hours: Int,
        minutes: Int,
        amPm: String,
        isReapeated: Boolean,
        isAllDay: Boolean,
        days: List<String>,
    ) {
        _reminderSelectedTime.value = ReminderTime(
            hour = hours,
            minute = minutes,
            ampm = amPm,
            isRepeated = isReapeated,
            isAllDay = isAllDay,
            days = days,
        )
    }

    val reminderTime = reminderTimeRepository.getReminderTime()

    fun insertRemindTime(reminderTime: ReminderTime) {
        viewModelScope.launch {
            reminderTimeRepository.insertReminderTime(reminderTime)
        }
    }

    fun updateRemindTime(reminderTime: ReminderTime) {
        viewModelScope.launch {
            reminderTimeRepository.updateReminderTime(reminderTime)
        }
    }

    fun deleteRemindTime(reminderTime: ReminderTime) {
        viewModelScope.launch {
            reminderTimeRepository.deleteReminderTime(reminderTime)
        }
    }

    fun deleteAllRemindTime() {
        viewModelScope.launch {
            reminderTimeRepository.dellAllReminderTimes()
        }
    }
}
