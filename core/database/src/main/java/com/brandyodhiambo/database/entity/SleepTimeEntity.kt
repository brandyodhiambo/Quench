package com.brandyodhiambo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.database.Constants.SLEEP_TIME_TABLE

@Entity(tableName = SLEEP_TIME_TABLE)
data class SleepTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hour: Int,
    val minute: Int,
    val ampm: String
)
