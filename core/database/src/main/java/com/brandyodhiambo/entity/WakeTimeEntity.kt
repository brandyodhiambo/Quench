package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.WAKE_TIME_TABLE

@Entity(tableName = WAKE_TIME_TABLE)
data class WakeTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: Int,
    val minute: Int,
    val ampm: String
)
