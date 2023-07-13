package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.LevelEntity

@Dao
interface LevelDao {

    @Insert
    suspend fun insertLevel(levelEntity: LevelEntity)

    @Query("SELECT *FROM level_table")
    fun getLevel(): LiveData<LevelEntity?>

    @Delete
    suspend fun deleteLevel(levelEntity: LevelEntity)

    @Query("UPDATE level_table SET amountTaken = :amountTaken, waterTaken = :waterTaken WHERE id = :id")
    suspend fun updateLevel(id: Int, amountTaken: Float, waterTaken: Int)

    @Query("DELETE FROM level_table")
    suspend fun deleteAllLevel()
}
