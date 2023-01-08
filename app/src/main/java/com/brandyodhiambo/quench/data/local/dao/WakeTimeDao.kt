package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.WakeTimeEntity

@Dao
interface WakeTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWakeTime(wakeTime: WakeTimeEntity)

    @Query("DELETE FROM wake_time_table")
    suspend fun deleteWakeTime()

    @Query("SELECT * FROM wake_time_table")
    fun getWakeTime(): WakeTimeEntity
}