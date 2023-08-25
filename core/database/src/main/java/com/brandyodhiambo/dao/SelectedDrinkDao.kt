/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    suspend fun updateSelectedDrink(id: Int, drinkValue: String, time: String, icon: Int)

    @Query("DELETE FROM selected_drink_table")
    suspend fun deleteAllSelectedDrink()
}
