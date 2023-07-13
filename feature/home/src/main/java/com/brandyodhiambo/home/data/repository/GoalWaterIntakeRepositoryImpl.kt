package com.brandyodhiambo.home.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.repository.GoalWaterIntakeRepository
import com.brandyodhiambo.dao.GoalWaterIntakeDao
import com.brandyodhiambo.home.data.mapper.toGoalWaterIntake
import com.brandyodhiambo.home.data.mapper.toGoalWaterIntakeEntity

class GoalWaterIntakeRepositoryImpl(
    private val goalWaterIntakeDao: GoalWaterIntakeDao,
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