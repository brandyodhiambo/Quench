package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.SelectedDrinkEntity

@Dao
interface SelectedDrinkDao {

    @Insert
    suspend fun insertSelectedDrink(selectedDrink: SelectedDrinkEntity)

    @Query("SELECT * FROM selected_drink_table")
    fun getAllSelectedDrink():LiveData<List<SelectedDrinkEntity>>

    @Query("SELECT * FROM selected_drink_table WHERE id = :id")
    fun getOneSelectedDrink(id:Int):SelectedDrinkEntity

    @Delete
    suspend fun deleteOneSelectedDrink(selectedDrink: SelectedDrinkEntity)

    @Query("DELETE FROM selected_drink_table")
    suspend fun deleteAllSelectedDrink()
}