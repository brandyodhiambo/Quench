package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.WakeTimeEntity

@Dao
interface WakeTimeDao {

    @Insert
    suspend fun insertWakeTime(wakeTimeEntity: WakeTimeEntity)

    @Query("SELECT *FROM wake_time_table")
    fun getWakeTime(): LiveData<WakeTimeEntity?>

    @Delete
    suspend fun deleteWakeTime(wakeTimeEntity: WakeTimeEntity)

    @Query("UPDATE wake_time_table SET hour = :hours, minute = :minutes, ampm = :ampm WHERE id = :id")
    suspend fun updateWakeTime(id: Int,hours: String, minutes: String,ampm:String)

    @Query("DELETE FROM wake_time_table")
    suspend fun deleteAllWakeTime()
}