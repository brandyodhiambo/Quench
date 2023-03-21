package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.REMINDER_TABLE

@Entity(tableName = REMINDER_TABLE)
data class ReminderTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hour: Int,
    val minute: Int,
    val ampm: String,
    val isRepeated: Boolean,
    val isAllDay: Boolean,
    val days:List<String>
)
