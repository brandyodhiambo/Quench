package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.IDEAL_WATER_TABLE

@Entity(tableName = IDEAL_WATER_TABLE)
data class IdealWaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val waterIntake: Int,
    val form:String
)
