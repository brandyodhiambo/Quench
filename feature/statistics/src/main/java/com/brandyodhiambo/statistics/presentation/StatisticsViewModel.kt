package com.brandyodhiambo.statistics.presentation

import androidx.lifecycle.ViewModel
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val dailyStatisticsRepository: DailyStatisticsRepository,
    private val weeklyStatisticsRepository: WeeklyStatisticRepository,
    private val monthlyStatisticsRepository: MonthlyStatisticsRepository,
) : ViewModel() {

    val dailyStatisticsFromDB = dailyStatisticsRepository.getDailyStatistics()
    val weeklyStatisticsFromDB = weeklyStatisticsRepository.getWeeklyStatistic()
    val monthlyStatisticsFromDB = monthlyStatisticsRepository.getMonthlyStatistics()
}
