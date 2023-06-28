package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.common.domain.repository.WakeTimeRepository
import com.brandyodhiambo.dao.WakeTimeDao
import com.brandyodhiambo.home.data.mapper.toWakeTime
import com.brandyodhiambo.home.data.mapper.toWakeTimeEntity

class WakeTimeRepositoryImpl(
    private val wakeTimeDao: WakeTimeDao,
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
            ampm = entityData.ampm,
        )
    }

    override fun getWakeTime(): LiveData<WakeTime?> {
        return Transformations.map(wakeTimeDao.getWakeTime()) { wakeTimeEntity ->
            wakeTimeEntity?.toWakeTime()
        }
    }

    override suspend fun deleteWakeTime(wakeTime: WakeTime) {
        wakeTimeDao.deleteWakeTime(wakeTime.toWakeTimeEntity())
    }

    override suspend fun dellAllWakeTimes() {
        wakeTimeDao.deleteAllWakeTime()
    }
}
