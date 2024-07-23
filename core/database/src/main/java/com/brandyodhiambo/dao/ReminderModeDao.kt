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
    fun getReminderMode(): LiveData<ReminderModeEntity?>

    @Delete
    suspend fun deleteReminderMode(ReminderModeEntity: ReminderModeEntity)

    @Query("UPDATE reminder_mode_table SET day = :day, isOn = :isOn WHERE id = :id")
    suspend fun updateReminderMode(id: Int, day: String, isOn: Boolean)

    @Query("DELETE FROM reminder_mode_table")
    suspend fun deleteAllReminderMode()
}