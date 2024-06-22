package com.brandyodhiambo.settings.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.common.domain.repository.TimeFormateRepository
import com.brandyodhiambo.dao.TimeFormateDao
import com.brandyodhiambo.settings.data.mapper.toTimeFormate
import com.brandyodhiambo.settings.data.mapper.toTimeFormateEntity

class TimeFormateRepositoryImpl(
    private val timeFormateDao: TimeFormateDao
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
}