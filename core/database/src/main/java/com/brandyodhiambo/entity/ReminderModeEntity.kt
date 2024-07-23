package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.REMINDER_MODE

@Entity(tableName = REMINDER_MODE)
data class ReminderModeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    val isOn: Boolean
)
