package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.common.domain.repository.ReminderTimeRepository
import com.brandyodhiambo.dao.ReminderTimeDao
import com.brandyodhiambo.home.data.mapper.toReminderEntity
import com.brandyodhiambo.home.data.mapper.toReminderTime
import com.brandyodhiambo.home.data.mapper.toWakeTime

class ReminderTimeRepositoryImpl(
    private val reminderTimeDao: ReminderTimeDao
) : ReminderTimeRepository {
    override suspend fun insertReminderTime(reminderTime: ReminderTime) {
        reminderTimeDao.insertReminderTime(reminderTime.toReminderEntity())
    }

    override suspend fun updateReminderTime(reminderTime: ReminderTime) {
        val entityData = reminderTime.toReminderEntity()
        reminderTimeDao.updateReminderTime(
            id = entityData.id,
            hours = entityData.hour.toString(),
            minutes = entityData.minute.toString(),
            ampm = entityData.ampm,
            isRepeated = entityData.isRepeated,
            isAllDay = entityData.isAllDay,
            days = entityData.days,
        )
    }

    override fun getReminderTime(): LiveData<ReminderTime?> {
        return Transformations.map(reminderTimeDao.getReminderTime()) { reminderTimeEntity ->
            reminderTimeEntity?.toReminderTime()
        }
    }

    override suspend fun deleteReminderTime(reminderTime: ReminderTime) {
        reminderTimeDao.deleteReminderTime(reminderTime.toReminderEntity())
    }

    override suspend fun dellAllReminderTimes() {
        reminderTimeDao.deleteAllReminderTime()
    }
}