package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.MONTHLY_STATISTICS_TABLE

@Entity(tableName = MONTHLY_STATISTICS_TABLE)
data class MonthlyStatisticsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amountTaken: Float,
    val month: String,
)
