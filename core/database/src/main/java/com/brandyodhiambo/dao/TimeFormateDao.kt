package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.TimeFormateEntity

@Dao
interface TimeFormateDao {

    @Insert
    suspend fun insertTimeFormate(timeFormateEntity: TimeFormateEntity)

    @Query("SELECT * FROM time_formate_table")
    fun getTimeFormate():LiveData<TimeFormateEntity>

    @Delete
    suspend fun deleteTimeFormate(timeFormateEntity: TimeFormateEntity)
}
