package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.MonthlyStatisticsEntity

@Dao
interface MonthlyStatisticsDao {
    @Insert
    suspend fun insertMonthlyStatistic(monthlyStatisticsEntity: MonthlyStatisticsEntity)

    @Query("SELECT *FROM monthly_statistics_table")
    fun getMonthlyStatistics(): LiveData<List<MonthlyStatisticsEntity>?>

    @Delete
    suspend fun deleteMonthlyStatistic(monthlyStatisticsEntity: MonthlyStatisticsEntity)

    @Query("UPDATE monthly_statistics_table SET amountTaken = :amountTaken, month = :month WHERE id = :id")
    suspend fun updateMonthlyStatistic(id: Int, amountTaken: Float, month: String)

    @Query("DELETE FROM monthly_statistics_table")
    suspend fun deleteAllMonthlyStatistics()
}
