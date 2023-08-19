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
