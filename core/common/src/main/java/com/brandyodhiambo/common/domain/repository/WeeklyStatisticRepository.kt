package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.WeeklyStatistics

interface WeeklyStatisticRepository {
    suspend fun insertWeeklyStatistic(weeklyStatistic: WeeklyStatistics)
    suspend fun updateWeeklyStatistic(weeklyStatistic: WeeklyStatistics)
    fun getWeeklyStatistic(): LiveData<List<WeeklyStatistics>?>
    suspend fun deleteWeeklyStatistic(weeklyStatistic: WeeklyStatistics)
    suspend fun deleteAllWeeklyStatistic()
}
