package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.TimeFormate

interface TimeFormateRepository {

    suspend fun insertTimeFormate(timeFormate: TimeFormate)
    fun getTimeFormate(): LiveData<TimeFormate?>
    suspend fun deleteTimeFormate(timeFormate: TimeFormate)
    suspend fun deleteAllTimeFormate()
}