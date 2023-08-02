package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.MonthlyStatistics

interface MonthlyStatisticsRepository {

    suspend fun insertMonthlyStatistics(monthlyStatistics: MonthlyStatistics)
    suspend fun updateMonthlyStatistics(monthlyStatistics: MonthlyStatistics)
    fun getMonthlyStatistics(): LiveData<List<MonthlyStatistics>?>
    suspend fun deleteMonthlyStatistics(monthlyStatistics: MonthlyStatistics)
    suspend fun deleteAllMonthlyStatistics()
}
