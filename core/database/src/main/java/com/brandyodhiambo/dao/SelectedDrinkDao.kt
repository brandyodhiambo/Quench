package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.SelectedDrinkEntity

@Dao
interface SelectedDrinkDao {
    @Insert
    suspend fun insertSelectedDrink(selectedDrink: SelectedDrinkEntity)

    @Query("SELECT *FROM selected_drink_table")
    fun getSelectedDrink(): LiveData<List<SelectedDrinkEntity>>

    @Query("SELECT *FROM selected_drink_table ORDER BY id DESC")
    fun getAllSelectedDrink(): LiveData<List<SelectedDrinkEntity>>

    @Query("DELETE FROM selected_drink_table WHERE id = :id")
    suspend fun deleteSelectedDrink(id: Int)

    @Query("UPDATE selected_drink_table SET drinkValue = :drinkValue, time = :time , icon = :icon  WHERE id = :id")
    suspend fun updateSelectedDrink(id: Int,drinkValue: String, time: String,icon: Int)

    @Query("DELETE FROM selected_drink_table")
    suspend fun deleteAllSelectedDrink()
}