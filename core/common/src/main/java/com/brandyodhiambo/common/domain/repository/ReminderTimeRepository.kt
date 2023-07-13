package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.ReminderTime

interface ReminderTimeRepository {
    suspend fun insertReminderTime(reminderTime: ReminderTime)
    suspend fun updateReminderTime(reminderTime: ReminderTime)
    fun getReminderTime(): LiveData<ReminderTime?>
    suspend fun deleteReminderTime(reminderTime: ReminderTime)
    suspend fun dellAllReminderTimes()
}
