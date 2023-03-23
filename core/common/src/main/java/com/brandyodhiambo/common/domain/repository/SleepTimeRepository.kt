package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.SleepTime

interface SleepTimeRepository {

    suspend fun insertSleepTime(sleepTime: SleepTime)
    suspend fun updateSleepTime(sleepTime: SleepTime)
    fun getSleepTime(): LiveData<SleepTime?>
    suspend fun deleteSleepTime(sleepTime: SleepTime)
    suspend fun dellAllSleepTimes()
}