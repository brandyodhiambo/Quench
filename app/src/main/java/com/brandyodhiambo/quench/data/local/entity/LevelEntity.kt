package com.brandyodhiambo.quench.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.quench.utils.Constants.LEVEL_TABLE

@Entity(tableName = LEVEL_TABLE)
data class LevelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: String,
    val day: String,
    val month: String
)
