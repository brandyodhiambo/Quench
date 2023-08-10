package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.ACHIEVEMENT_TABLE

@Entity(tableName = ACHIEVEMENT_TABLE)
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isAchieved: Boolean,
    val day: String,
)