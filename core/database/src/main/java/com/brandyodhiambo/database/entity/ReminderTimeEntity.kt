package com.brandyodhiambo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.common.domain.model.Day
import com.brandyodhiambo.database.Constants.REMINDER_TABLE

@Entity(tableName = REMINDER_TABLE)
data class ReminderTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hour: Int,
    val minute: Int,
    val ampm: String,
    val repeat: String,
    val day: Day
)
