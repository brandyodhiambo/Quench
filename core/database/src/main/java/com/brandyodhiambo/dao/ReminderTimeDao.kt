package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.ReminderTimeEntity

@Dao
interface ReminderTimeDao {
    @Insert
    suspend fun insertReminderTime(reminderTime: ReminderTimeEntity)

    @Query("SELECT *FROM reminder_table")
    fun getReminderTime(): LiveData<ReminderTimeEntity>

    @Delete
    suspend fun deleteReminderTime(reminderTime: ReminderTimeEntity)

    @Query("UPDATE reminder_table SET hour = :hours, minute = :minutes,ampm=:ampm,isRepeated = :isRepeated, isAllDay=:isAllDay,days =:days WHERE id = :id")
    suspend fun updateReminderTime(
        id: Int,
        hours: String,
        minutes: String,
        ampm: String,
        isRepeated: Boolean,
        isAllDay: Boolean,
        days: List<String>,
    )

    @Query("DELETE FROM reminder_table")
    suspend fun deleteAllReminderTime()
}