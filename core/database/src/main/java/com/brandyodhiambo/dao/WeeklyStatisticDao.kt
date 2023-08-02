package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.WeeklyStatisticsEntity

@Dao
interface WeeklyStatisticDao {
    @Insert
    suspend fun insertWeeklyStatistic(weeklyStatisticsEntity: WeeklyStatisticsEntity)

    @Query("SELECT *FROM weekly_statistics_table")
    fun getWeeklyStatistics(): LiveData<List<WeeklyStatisticsEntity>?>

    @Delete
    suspend fun deleteWeeklyStatistic(weeklyStatisticsEntity: WeeklyStatisticsEntity)

    @Query("UPDATE weekly_statistics_table SET amountTaken = :amountTaken, week = :week WHERE id = :id")
    suspend fun updateWeeklyStatistic(id: Int, amountTaken: Float, week: String)

    @Query("DELETE FROM weekly_statistics_table")
    suspend fun deleteAllWeeklyStatistics()
}
