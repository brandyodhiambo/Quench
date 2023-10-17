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
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.common.domain.repository.WakeTimeRepository
import com.brandyodhiambo.dao.WakeTimeDao
import com.brandyodhiambo.home.data.mapper.toWakeTime
import com.brandyodhiambo.home.data.mapper.toWakeTimeEntity

class WakeTimeRepositoryImpl(
    private val wakeTimeDao: WakeTimeDao
) : WakeTimeRepository {
    override suspend fun insertWakeTime(wakeTime: WakeTime) {
        wakeTimeDao.insertWakeTime(wakeTime.toWakeTimeEntity())
    }

    override suspend fun updateWakeTime(wakeTime: WakeTime) {
        val entityData = wakeTime.toWakeTimeEntity()
        wakeTimeDao.updateWakeTime(
            id = entityData.id,
            hours = entityData.hour.toString(),
            minutes = entityData.minute.toString(),
            ampm = entityData.ampm
        )
    }

    override fun getWakeTime(): LiveData<WakeTime?> {
        return wakeTimeDao.getWakeTime().map {
            it?.toWakeTime()
        }
    }

    override suspend fun deleteWakeTime(wakeTime: WakeTime) {
        wakeTimeDao.deleteWakeTime(wakeTime.toWakeTimeEntity())
    }

    override suspend fun dellAllWakeTimes() {
        wakeTimeDao.deleteAllWakeTime()
    }
}
