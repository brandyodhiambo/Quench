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
):SleepTimeRepository {
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

    override fun getSleepTime(): LiveData<SleepTime> {
        return Transformations.map(sleepTimeDao.getSleepTime()){ sleepTimeEntity ->
            sleepTimeEntity.toSleepTime()
        }
    }

    override suspend fun deleteSleepTime(sleepTime: SleepTime) {
       sleepTimeDao.deleteSleepTime(sleepTime.toSleepTimeEntity())
    }

    override suspend fun dellAllSleepTimes() {
        sleepTimeDao.deleteAllSleepTime()
    }
}