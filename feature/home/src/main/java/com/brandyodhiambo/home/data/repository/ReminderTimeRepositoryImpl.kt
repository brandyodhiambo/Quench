/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.common.domain.repository.ReminderTimeRepository
import com.brandyodhiambo.dao.ReminderTimeDao
import com.brandyodhiambo.home.data.mapper.toReminderEntity
import com.brandyodhiambo.home.data.mapper.toReminderTime

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
            ampm = entityData.ampm ?: "",
            isRepeated = entityData.isRepeated ?: false,
            isAllDay = entityData.isAllDay ?: false,
            days = entityData.days ?: listOf()
        )
    }

    override fun getReminderTime(): LiveData<ReminderTime?> {
        return reminderTimeDao.getReminderTime().map { reminderTimeEntity ->
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
