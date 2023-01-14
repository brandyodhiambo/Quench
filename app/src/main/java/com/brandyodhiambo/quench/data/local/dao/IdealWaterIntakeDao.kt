package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.IdealWaterIntakeEntity

@Dao
interface IdealWaterIntakeDao {
    @Insert
    suspend fun insertIdealWaterIntake(idealWaterIntakeEntity: IdealWaterIntakeEntity)

    @Query("SELECT * FROM ideal_water_table WHERE id = :id")
    fun getIdealWaterIntake(id:Int): IdealWaterIntakeEntity

    @Query("SELECT * FROM ideal_water_table ORDER BY id DESC")
    fun getAllIdealWaterIntakes(): LiveData<List<IdealWaterIntakeEntity>>

    @Query("DELETE FROM ideal_water_table")
    suspend fun deleteAllIdealWaterIntakes()

}