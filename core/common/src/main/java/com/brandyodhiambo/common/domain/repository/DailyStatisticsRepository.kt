package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.DailyStatistics

interface DailyStatisticsRepository {
    suspend fun insertDailyStatistics(dailyStatistics: DailyStatistics)
    suspend fun updateDailyStatistics(dailyStatistics: DailyStatistics)
    suspend fun deleteDailyStatistics(dailyStatistics: DailyStatistics)
    suspend fun deleteAllDailyStatistics()
    fun getDailyStatistics(): LiveData<List<DailyStatistics>?>
}
