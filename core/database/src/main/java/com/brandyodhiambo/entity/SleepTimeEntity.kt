package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.SLEEP_TIME_TABLE

@Entity(tableName = SLEEP_TIME_TABLE)
data class SleepTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: Int,
    val minute: Int,
    val ampm: String
)
