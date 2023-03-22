package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.SleepTimeEntity

@Dao
interface SleepTimeDao {
    @Insert
    suspend fun insertSleepTime(sleepTimeEntity: SleepTimeEntity)

    @Query("SELECT *FROM sleep_time_table")
    fun getSleepTime(): LiveData<SleepTimeEntity>

    @Delete
    suspend fun deleteSleepTime(sleepTimeEntity: SleepTimeEntity)

    @Query("UPDATE sleep_time_table SET hour = :hours, minute = :minutes, ampm = :ampm WHERE id = :id")
    suspend fun updateSleepTime(id: Int,hours: String, minutes: String,ampm:String)

    @Query("DELETE FROM sleep_time_table")
    suspend fun deleteAllSleepTime()
}