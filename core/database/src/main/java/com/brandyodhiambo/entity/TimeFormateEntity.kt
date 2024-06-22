package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.TIME_FORMATE_TABLE

@Entity(tableName = TIME_FORMATE_TABLE)
data class TimeFormateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val formate: Int,
)