package com.brandyodhiambo.settings.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.brandyodhiambo.common.domain.model.ReminderMode
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.domain.repository.TimeFormateRepository
import com.brandyodhiambo.dao.ReminderModeDao
import com.brandyodhiambo.dao.TimeFormateDao
import com.brandyodhiambo.settings.data.mapper.toReminderMode
import com.brandyodhiambo.settings.data.mapper.toReminderModeEntity
import com.brandyodhiambo.settings.data.mapper.toTimeFormate
import com.brandyodhiambo.settings.data.mapper.toTimeFormateEntity

class TimeFormateRepositoryImpl(
    private val timeFormateDao: TimeFormateDao,
    private val reminderModeDao: ReminderModeDao
):TimeFormateRepository {
    override suspend fun insertTimeFormate(timeFormate: TimeFormate) {
        timeFormateDao.insertTimeFormate(timeFormate.toTimeFormateEntity())
    }

    override fun getTimeFormate(): LiveData<TimeFormate?> {
        return timeFormateDao.getTimeFormate().map {
            it.toTimeFormate()
        }
    }

    override suspend fun deleteTimeFormate(timeFormate: TimeFormate) {
        timeFormateDao.deleteTimeFormate(timeFormate.toTimeFormateEntity())
    }

    override suspend fun deleteAllTimeFormate() {
        timeFormateDao.deleteAllTimeFormate()
    }

    override suspend fun insertReminderMode(reminderMode: ReminderMode) {
        reminderModeDao.insertReminderMode(reminderMode.toReminderModeEntity())
    }

    override suspend fun updateReminderMode(reminderMode: ReminderMode) {
         val entityData = reminderMode.toReminderModeEntity()
        reminderModeDao.updateReminderMode(
            id = entityData.id,
            day = entityData.day,
            isOn = entityData.isOn
        )
    }

    override fun getReminderMode(): LiveData<ReminderMode?> {
        return reminderModeDao.getReminderMode().map {
            it?.toReminderMode()
        }
    }

    override suspend fun deleteReminderMode(reminderMode: ReminderMode) {
        reminderModeDao.deleteReminderMode(reminderMode.toReminderModeEntity())
    }

    override suspend fun deleteAllReminderMode() {
       reminderModeDao.deleteAllReminderMode()
    }
}