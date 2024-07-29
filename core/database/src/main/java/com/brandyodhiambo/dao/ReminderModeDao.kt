package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.ReminderModeEntity

@Dao
interface ReminderModeDao {
    @Insert
    suspend fun insertReminderMode(reminderModeEntity: ReminderModeEntity)

    @Query("SELECT *FROM reminder_mode_table")
    fun getReminderMode(): LiveData<List<ReminderModeEntity>?>

    @Delete
    suspend fun deleteReminderMode(reminderModeEntity: ReminderModeEntity)

    @Query("UPDATE reminder_mode_table SET days = :days, hour =:hour, minutes = :minutes, ampm = :ampm,mode=:mode, isVibrated = :isVibrated, isDeleted = :isDeleted, isOn = :isOn WHERE id = :id")
    suspend fun updateReminderMode(
        id: Int,
        days: List<String>,
        mode:String,
        hour:Int,
        minutes:Int,
        ampm:String,
        isVibrated:Boolean,
        isDeleted:Boolean,
        isOn: Boolean
    )

    @Query("DELETE FROM reminder_mode_table")
    suspend fun deleteAllReminderMode()
}