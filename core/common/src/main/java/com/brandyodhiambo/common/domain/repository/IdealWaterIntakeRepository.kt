package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.IdealWaterIntake

interface IdealWaterIntakeRepository {
    suspend fun insertIdealWaterIntake(idealWaterIntake: IdealWaterIntake)
    suspend fun updateIdealWaterIntake(idealWaterIntake: IdealWaterIntake)
    suspend fun deleteIdealWaterIntake(idealWaterIntake: IdealWaterIntake)
    suspend fun deleteAllIdealWaterIntakes()
    fun getIdealWaterIntake(): LiveData<IdealWaterIntake?>
}