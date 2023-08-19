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
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.repository.SleepTimeRepository
import com.brandyodhiambo.dao.SleepTimeDao
import com.brandyodhiambo.home.data.mapper.toSleepTime
import com.brandyodhiambo.home.data.mapper.toSleepTimeEntity

class SleepTimeRepositoryImpl(
    private val sleepTimeDao: SleepTimeDao
) : SleepTimeRepository {
    override suspend fun insertSleepTime(sleepTime: SleepTime) {
        sleepTimeDao.insertSleepTime(sleepTime.toSleepTimeEntity())
    }

    override suspend fun updateSleepTime(sleepTime: SleepTime) {
        val entityResult = sleepTime.toSleepTimeEntity()
        sleepTimeDao.updateSleepTime(
            id = entityResult.id,
            hours = entityResult.hour.toString(),
            minutes = entityResult.minute.toString(),
            ampm = entityResult.ampm
        )
    }

    override fun getSleepTime(): LiveData<SleepTime?> {
        return Transformations.map(sleepTimeDao.getSleepTime()) { sleepTimeEntity ->
            sleepTimeEntity?.toSleepTime()
        }
    }

    override suspend fun deleteSleepTime(sleepTime: SleepTime) {
        sleepTimeDao.deleteSleepTime(sleepTime.toSleepTimeEntity())
    }

    override suspend fun dellAllSleepTimes() {
        sleepTimeDao.deleteAllSleepTime()
    }
}
