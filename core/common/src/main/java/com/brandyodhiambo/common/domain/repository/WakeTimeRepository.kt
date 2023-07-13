package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.WakeTime

interface WakeTimeRepository {

    suspend fun insertWakeTime(wakeTime: WakeTime)
    suspend fun updateWakeTime(wakeTime: WakeTime)
    fun getWakeTime(): LiveData<WakeTime?>
    suspend fun deleteWakeTime(wakeTime: WakeTime)
    suspend fun dellAllWakeTimes()
}