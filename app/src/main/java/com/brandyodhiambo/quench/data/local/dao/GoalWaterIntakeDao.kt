package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.GoalWaterIntakeEntity

@Dao
interface GoalWaterIntakeDao {
    @Insert
    suspend fun insertGoalWaterIntake(goalWaterIntakeEntity: GoalWaterIntakeEntity)

    @Query("SELECT * FROM goal_table WHERE id = :id")
    fun getGoalWaterIntake(id:Int): GoalWaterIntakeEntity

    @Query("SELECT * FROM goal_table ORDER BY id DESC")
    fun getAllGoalWaterIntakes(): LiveData<List<GoalWaterIntakeEntity>>

    @Query("DELETE FROM goal_table")
    suspend fun deleteAllGoalWaterIntakes()



}