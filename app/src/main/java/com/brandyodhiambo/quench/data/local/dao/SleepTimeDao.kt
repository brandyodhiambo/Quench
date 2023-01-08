package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.SleepTimeEntity

@Dao
interface SleepTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepTime(sleepTime: SleepTimeEntity)

    @Query("SELECT * FROM sleep_time_table")
    fun getSleepTime():LiveData<SleepTimeEntity>

    @Query("DELETE FROM sleep_time_table")
    suspend fun deleteSleepTime()
}