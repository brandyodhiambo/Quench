package com.brandyodhiambo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brandyodhiambo.Constants.SELECTED_DRINK_TABLE

@Entity(tableName = SELECTED_DRINK_TABLE)
data class SelectedDrinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val drinkValue: String,
    val time: String,
    val icon: Int
)
