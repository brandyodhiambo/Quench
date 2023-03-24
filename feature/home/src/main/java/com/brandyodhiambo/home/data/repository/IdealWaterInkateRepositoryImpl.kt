package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.dao.IdealWaterIntakeDao
import com.brandyodhiambo.home.data.mapper.toIdealWaterIntake
import com.brandyodhiambo.home.data.mapper.toIdealWaterIntakeEntity

class IdealWaterInkateRepositoryImpl(
    private val idealWaterIntakeDao: IdealWaterIntakeDao
) : IdealWaterIntakeRepository {

    override suspend fun insertIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        idealWaterIntakeDao.insertIdealWaterIntake(idealWaterIntake.toIdealWaterIntakeEntity())
    }

    override suspend fun updateIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        val entityData = idealWaterIntake.toIdealWaterIntakeEntity()
        idealWaterIntakeDao.updateIdealIntake(
            entityData.id,
            entityData.waterIntake.toString(),
            entityData.form
        )
    }

    override suspend fun deleteIdealWaterIntake(idealWaterIntake: IdealWaterIntake) {
        idealWaterIntakeDao.deleteIdealIntake(idealWaterIntake.toIdealWaterIntakeEntity())
    }

    override suspend fun deleteAllIdealWaterIntakes() {
        idealWaterIntakeDao.deleteAllIdealIntake()
    }

    override fun getIdealWaterIntake(): LiveData<IdealWaterIntake?> {
        return Transformations.map(idealWaterIntakeDao.getIdealWaterIntake()) { idealWaterIntakeEntity ->
            idealWaterIntakeEntity?.toIdealWaterIntake()
        }
    }
}
