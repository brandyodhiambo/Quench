package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.REMINDER_MODE

@Entity(tableName = REMINDER_MODE)
data class ReminderModeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mode:String,
    val days: List<String>,
    val isVibrated:Boolean,
    val isDeleted: Boolean,
    val hour:Int,
    val minutes:Int,
    val ampm:String,
    val isOn:Boolean
)
