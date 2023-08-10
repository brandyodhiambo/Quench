package com.brandyodhiambo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.AchievementEntity

@Dao
interface AchievementDao {

    @Insert
    suspend fun insertAchievement(achievementEntity: AchievementEntity)

    @Query("SELECT *FROM achievement_table")
    fun getAchievement(): LiveData<List<AchievementEntity>?>

    @Query("DELETE FROM achievement_table")
    suspend fun deleteAllAchievement()

    @Query("UPDATE achievement_table SET isAchieved = :isAchieved,day = :day WHERE id = :id")
    suspend fun updateAchievement(id: Int, isAchieved: Boolean, day: String)

    @Query("DELETE FROM achievement_table WHERE id = :id")
    suspend fun deleteAchievement(id: Int)
}
