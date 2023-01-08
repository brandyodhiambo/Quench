package com.brandyodhiambo.quench.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.quench.utils.Constants.REMINDER_TABLE
import com.brandyodhiambo.quench.views.screens.notification.Day

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
