package com.brandyodhiambo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.database.Constants.REPORT_TABLE

@Entity(tableName = REPORT_TABLE)
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val weekAverage: String,
    val monthAverage: String,
    val CompletionAverage: String,
    val drinkFrequency: String
)
