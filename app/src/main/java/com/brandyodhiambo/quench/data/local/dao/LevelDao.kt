package com.brandyodhiambo.quench.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.LevelEntity

@Dao
interface LevelDao {
    @Insert
    suspend fun insertLevel(levelEntity: LevelEntity)

    @Query("SELECT * FROM level_table WHERE id = :id")
    fun getLevel(id:Int): LevelEntity

    @Query("SELECT * FROM level_table ORDER BY id DESC")
    fun getAllLevels(): LiveData<List<LevelEntity>>

    @Query("DELETE FROM level_table")
    suspend fun deleteAllLevels()
}