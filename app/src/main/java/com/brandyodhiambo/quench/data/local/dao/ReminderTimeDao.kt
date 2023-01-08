package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.ReminderTimeEntity

@Dao
interface ReminderTimeDao {

    @Insert
    suspend fun insertReminderTime(reminderTimeEntity: ReminderTimeEntity)

    @Query("SELECT * FROM reminder_table ORDER BY id DESC")
    fun getAllReminderTimes(): LiveData<List<ReminderTimeEntity>>

    @Query("Select * FROM reminder_table WHERE id = :id")
    fun getOneReminderTime(id:Int): ReminderTimeEntity

    @Delete
    suspend fun deleteReminderTime(reminderTimeEntity: ReminderTimeEntity)

    @Query("DELETE FROM reminder_table")
    suspend fun deleteAllReminderTimes()
}