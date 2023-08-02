package com.brandyodhiambo.statistics.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.dao.DailyStatisticsDao
import com.brandyodhiambo.statistics.data.mapper.toDailyStatistics
import com.brandyodhiambo.statistics.data.mapper.toDailyStatisticsEntity

class DailyStatisticsRepositoryImpl(
    private val dailyStatisticsDao: DailyStatisticsDao,
) : DailyStatisticsRepository {
    override suspend fun insertDailyStatistics(dailyStatistics: DailyStatistics) {
        dailyStatisticsDao.insertDailyStatistic(dailyStatistics.toDailyStatisticsEntity())
    }

    override suspend fun updateDailyStatistics(dailyStatistics: DailyStatistics) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDailyStatistics(dailyStatistics: DailyStatistics) {
        dailyStatisticsDao.deleteDailyStatistic(dailyStatistics.toDailyStatisticsEntity())
    }

    override suspend fun deleteAllDailyStatistics() {
        dailyStatisticsDao.deleteAllDailyStatistics()
    }

    override fun getDailyStatistics(): LiveData<List<DailyStatistics>?> {
        return Transformations.map(dailyStatisticsDao.getDailyStatistics()) { dailyStatisticsEntity ->
            dailyStatisticsEntity?.map { it.toDailyStatistics() }
        }
    }
}
