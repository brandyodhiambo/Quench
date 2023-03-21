package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.GOAL_TABLE

@Entity(tableName = GOAL_TABLE)
data class GoalWaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val waterIntake: Int,
    val form: String
)
