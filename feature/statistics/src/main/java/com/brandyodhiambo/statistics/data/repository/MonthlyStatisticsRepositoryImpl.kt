package com.brandyodhiambo.statistics.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.dao.MonthlyStatisticsDao
import com.brandyodhiambo.statistics.data.mapper.toMonthlyStatistics
import com.brandyodhiambo.statistics.data.mapper.toMonthlyStatisticsEntity

class MonthlyStatisticsRepositoryImpl(
    private val monthlyStatisticalDao: MonthlyStatisticsDao,
) : MonthlyStatisticsRepository {
    override suspend fun insertMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        monthlyStatisticalDao.insertMonthlyStatistic(monthlyStatistics.toMonthlyStatisticsEntity())
    }

    override suspend fun updateMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        TODO("Not yet implemented")
    }

    override fun getMonthlyStatistics(): LiveData<List<MonthlyStatistics>?> {
        return Transformations.map(monthlyStatisticalDao.getMonthlyStatistics()) { monthlyStatisticsEntity ->
            monthlyStatisticsEntity?.map { it.toMonthlyStatistics() }
        }
    }

    override suspend fun deleteMonthlyStatistics(monthlyStatistics: MonthlyStatistics) {
        monthlyStatisticalDao.deleteMonthlyStatistic(monthlyStatistics.toMonthlyStatisticsEntity())
    }

    override suspend fun deleteAllMonthlyStatistics() {
        monthlyStatisticalDao.deleteAllMonthlyStatistics()
    }
}
