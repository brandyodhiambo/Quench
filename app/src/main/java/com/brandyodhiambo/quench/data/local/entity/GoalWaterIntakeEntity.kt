package com.brandyodhiambo.quench.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.quench.utils.Constants.GOAL_TABLE

@Entity(tableName = GOAL_TABLE)
data class GoalWaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val waterIntake: Int,
    val form:String
)
