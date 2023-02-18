package com.brandyodhiambo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.database.Constants.SELECTED_DRINK_TABLE

@Entity(tableName = SELECTED_DRINK_TABLE)
data class SelectedDrinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value: String,
    val time: String,
)
