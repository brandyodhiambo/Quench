package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.WEEKLY_STATISTICS_TABLE

@Entity(tableName = WEEKLY_STATISTICS_TABLE)
data class WeeklyStatisticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amountTaken: Float,
    val week: String,
)
