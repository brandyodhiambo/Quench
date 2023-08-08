package com.brandyodhiambo.statistics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.LevelRepository
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.ReminderTimeRepository
import com.brandyodhiambo.common.domain.repository.SelectedDrinkRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val levelRepository: LevelRepository,
    private val goalWaterIntakeRepository: GoalWaterIntakeRepository,
    private val idealWaterIntakeRepository: IdealWaterIntakeRepository,
    private val selectedDrinkRepository: SelectedDrinkRepository,
    private val reminderTimeRepository: ReminderTimeRepository,
    private val dailyStatisticsRepository: DailyStatisticsRepository,
    private val weeklyStatisticsRepository: WeeklyStatisticRepository,
    private val monthlyStatisticsRepository: MonthlyStatisticsRepository,
) : ViewModel() {

    val levelFromDB = levelRepository.getLevel()

    /*Daily Statistics*/
    val dailyStatisticsFromDB = dailyStatisticsRepository.getDailyStatistics()
    fun insertDailyStatistic(dailyStatistics: DailyStatistics) {
        viewModelScope.launch {
            dailyStatisticsRepository.insertDailyStatistics(dailyStatistics)
        }
    }

    fun deleteAllDailyStatistics() {
        viewModelScope.launch {
            dailyStatisticsRepository.deleteAllDailyStatistics()
        }
    }

    fun deleteOneDailyStatistics(dailyStatistics: DailyStatistics) {
        viewModelScope.launch {
            dailyStatisticsRepository.deleteDailyStatistics(dailyStatistics)
        }
    }

    fun updateDailyStatistic(dailyStatistics: DailyStatistics) {
        viewModelScope.launch {
            dailyStatisticsRepository.updateDailyStatistics(dailyStatistics)
        }
    }

    fun emptyDBFromDailyData() {
        viewModelScope.launch {
            levelRepository.deleteAllLevel()
            goalWaterIntakeRepository.deleteAllGoalWaterIntakes()
            idealWaterIntakeRepository.deleteAllIdealWaterIntakes()
            selectedDrinkRepository.deleteAllSelectedDrinks()
            reminderTimeRepository.dellAllReminderTimes()
        }
    }

    /*Weekly Statistics*/
    val weeklyStatisticsFromDB = weeklyStatisticsRepository.getWeeklyStatistic()
    fun insertWeeklyStatistic(weeklyStatistics: WeeklyStatistics) {
        viewModelScope.launch {
            weeklyStatisticsRepository.insertWeeklyStatistic(weeklyStatistics)
        }
    }

    fun deleteAllWeeklyStatistics() {
        viewModelScope.launch {
            weeklyStatisticsRepository.deleteAllWeeklyStatistic()
        }
    }

    fun deleteOneWeeklyStatistics(weeklyStatistics: WeeklyStatistics) {
        viewModelScope.launch {
            weeklyStatisticsRepository.deleteWeeklyStatistic(weeklyStatistics)
        }
    }

    fun updateWeeklyStatistic(weeklyStatistics: WeeklyStatistics) {
        viewModelScope.launch {
            weeklyStatisticsRepository.updateWeeklyStatistic(weeklyStatistics)
        }
    }

    /*Monthly Statistics*/
    val monthlyStatisticsFromDB = monthlyStatisticsRepository.getMonthlyStatistics()

    fun insertMonthlyStatistic(monthlyStatistics: MonthlyStatistics) {
        viewModelScope.launch {
            monthlyStatisticsRepository.insertMonthlyStatistics(monthlyStatistics)
        }
    }

    fun deleteAllMonthlyStatistics() {
        viewModelScope.launch {
            monthlyStatisticsRepository.deleteAllMonthlyStatistics()
        }
    }

    fun deleteOneMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        viewModelScope.launch {
            monthlyStatisticsRepository.deleteMonthlyStatistics(monthlyStatistics)
        }
    }

    fun updateMonthlyStatistic(monthlyStatistics: MonthlyStatistics) {
        viewModelScope.launch {
            monthlyStatisticsRepository.updateMonthlyStatistics(monthlyStatistics)
        }
    }
}
