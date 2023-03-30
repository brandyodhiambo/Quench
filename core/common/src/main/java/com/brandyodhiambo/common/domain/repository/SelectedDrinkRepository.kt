package com.brandyodhiambo.common.domain.repository

import androidx.lifecycle.LiveData
import com.brandyodhiambo.common.domain.model.SelectedDrink

interface SelectedDrinkRepository {
    fun getSelectedDrink(): LiveData<List<SelectedDrink>>
    suspend fun insertSelectedDrink(selectedDrink: SelectedDrink)
    suspend fun deleteAllSelectedDrinks()
}