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
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.dao.GoalWaterIntakeDao
import com.brandyodhiambo.home.data.mapper.toGoalWaterIntake
import com.brandyodhiambo.home.data.mapper.toGoalWaterIntakeEntity

class GoalWaterIntakeRepositoryImpl(
    private val goalWaterIntakeDao: GoalWaterIntakeDao
) : GoalWaterIntakeRepository {
    override suspend fun insertGoalWaterIntake(goalWaterIntake: GoalWaterIntake) {
        goalWaterIntakeDao.insertGoalWaterIntake(goalWaterIntake.toGoalWaterIntakeEntity())
    }

    override suspend fun updateGoalWaterIntake(goalWaterIntake: GoalWaterIntake) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGoalWaterIntake(goalWaterIntake: GoalWaterIntake) {
        goalWaterIntakeDao.deleteGoalIntake(goalWaterIntake.toGoalWaterIntakeEntity())
    }

    override suspend fun deleteAllGoalWaterIntakes() {
        goalWaterIntakeDao.deleteAllGoalIntake()
    }

    override fun getGoalWaterIntake(): LiveData<GoalWaterIntake?> {
        return Transformations.map(goalWaterIntakeDao.getGoalWaterIntake()) { goalWaterIntakeEntity ->
            goalWaterIntakeEntity?.toGoalWaterIntake()
        }
    }
}
