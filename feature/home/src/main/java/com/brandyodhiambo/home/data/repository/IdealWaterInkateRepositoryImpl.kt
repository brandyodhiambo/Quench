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
        return idealWaterIntakeDao.getIdealWaterIntake().map { idealWaterIntakeEntity ->
            idealWaterIntakeEntity?.toIdealWaterIntake()
        }
    }
}
