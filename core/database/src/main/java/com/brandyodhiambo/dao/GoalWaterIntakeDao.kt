package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.GoalWaterIntakeEntity

@Dao
interface GoalWaterIntakeDao {

    @Insert
    suspend fun insertGoalWaterIntake(goalWaterIntake: GoalWaterIntakeEntity)

    @Query("SELECT *FROM goal_table")
    fun getGoalWaterIntake(): LiveData<GoalWaterIntakeEntity>

    @Delete
    suspend fun deleteGoalIntake(goalWaterIntake: GoalWaterIntakeEntity)

    @Query("UPDATE goal_table SET waterIntake = :waterIntake, form = :form WHERE id = :id")
    suspend fun updateGoalIntake(id: Int,waterIntake: String, form: String)

    @Query("DELETE FROM goal_table")
    suspend fun deleteAllGoalIntake()



}