package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.IdealWaterIntakeEntity

@Dao
interface IdealWaterIntakeDao {
    @Insert
    suspend fun insertIdealWaterIntake(idealWaterIntake: IdealWaterIntakeEntity)

    @Query("SELECT *FROM ideal_water_table")
    fun getIdealWaterIntake(): LiveData<IdealWaterIntakeEntity>

    @Delete
    suspend fun deleteIdealIntake(idealWaterIntake: IdealWaterIntakeEntity)

    @Query("UPDATE ideal_water_table SET waterIntake = :waterIntake, form = :form WHERE id = :id")
    suspend fun updateIdealIntake(id: Int,waterIntake: String, form: String)

    @Query("DELETE FROM ideal_water_table")
    suspend fun deleteAllIdealIntake()
}