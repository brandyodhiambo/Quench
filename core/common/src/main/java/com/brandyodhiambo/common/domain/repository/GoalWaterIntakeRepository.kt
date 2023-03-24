package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.GoalWaterIntake

interface GoalWaterIntakeRepository {
    suspend fun insertGoalWaterIntake(goalWaterIntake: GoalWaterIntake)
    suspend fun updateGoalWaterIntake(goalWaterIntake: GoalWaterIntake)
    suspend fun deleteGoalWaterIntake(goalWaterIntake: GoalWaterIntake)
    suspend fun deleteAllGoalWaterIntakes()
    fun getGoalWaterIntake(): LiveData<GoalWaterIntake?>
}