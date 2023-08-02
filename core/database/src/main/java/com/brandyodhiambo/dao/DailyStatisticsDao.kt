package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.DailyStatisticsEntity

@Dao
interface DailyStatisticsDao {

    @Insert
    suspend fun insertDailyStatistic(dailyStatisticsEntity: DailyStatisticsEntity)

    @Query("SELECT *FROM daily_statistics_table")
    fun getDailyStatistics(): LiveData<List<DailyStatisticsEntity>?>

    @Delete
    suspend fun deleteDailyStatistic(dailyStatisticsEntity: DailyStatisticsEntity)

    @Query("UPDATE daily_statistics_table SET amountTaken = :amountTaken, day = :day WHERE id = :id")
    suspend fun updateDailyStatistic(id: Int, amountTaken: Float, day: String)

    @Query("DELETE FROM daily_statistics_table")
    suspend fun deleteAllDailyStatistics()
}
