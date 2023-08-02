package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.DAILY_STATISTICS_TABLE

@Entity(tableName = DAILY_STATISTICS_TABLE)
data class DailyStatisticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amountTaken: Float,
    val day: String,
)
