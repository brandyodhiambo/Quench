package com.brandyodhiambo.quench.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.quench.utils.Constants.IDEAL_WATER_TABLE

@Entity(tableName = IDEAL_WATER_TABLE)
data class IdealWaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val waterIntake: Int,
    val form:String
)
