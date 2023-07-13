package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.LEVEL_TABLE

@Entity(tableName = LEVEL_TABLE)
data class LevelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amountTaken: Float,
    val waterTaken: Int,
)
