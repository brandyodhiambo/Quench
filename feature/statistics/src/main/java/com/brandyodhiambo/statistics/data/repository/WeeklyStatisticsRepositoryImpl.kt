package com.brandyodhiambo.statistics.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.dao.WeeklyStatisticDao
import com.brandyodhiambo.statistics.data.mapper.toWeeklyStatistics
import com.brandyodhiambo.statistics.data.mapper.toWeeklyStatisticsEntity

class WeeklyStatisticsRepositoryImpl(
    private val weeklyStatisticDao: WeeklyStatisticDao,
) : WeeklyStatisticRepository {
    override suspend fun insertWeeklyStatistic(weeklyStatistic: WeeklyStatistics) {
        weeklyStatisticDao.insertWeeklyStatistic(weeklyStatistic.toWeeklyStatisticsEntity())
    }

    override suspend fun updateWeeklyStatistic(weeklyStatistic: WeeklyStatistics) {
        TODO("Not yet implemented")
    }

    override fun getWeeklyStatistic(): LiveData<List<WeeklyStatistics>?> {
        return Transformations.map(weeklyStatisticDao.getWeeklyStatistics()) { weeklyStatisticsEntity ->
            weeklyStatisticsEntity?.map { it.toWeeklyStatistics() }
        }
    }

    override suspend fun deleteWeeklyStatistic(weeklyStatistic: WeeklyStatistics) {
        weeklyStatisticDao.deleteWeeklyStatistic(weeklyStatistic.toWeeklyStatisticsEntity())
    }

    override suspend fun deleteAllWeeklyStatistic() {
        weeklyStatisticDao.deleteAllWeeklyStatistics()
    }
}
