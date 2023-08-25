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
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.entity.DailyStatisticsEntity

@Dao
interface DailyStatisticsDao {

    @Insert
    suspend fun insertDailyStatistic(dailyStatisticsEntity: DailyStatisticsEntity)

    @Query("SELECT *FROM daily_statistics_table")
    fun getDailyStatistics(): LiveData<List<DailyStatisticsEntity>?>

    @Delete
    suspend fun deleteDailyStatistic(dailyStatisticsEntity: DailyStatisticsEntity)

    @Query("UPDATE daily_statistics_table SET amountTaken = :amountTaken, day = :day WHERE id = :id")
    suspend fun updateDailyStatistic(id: Int, amountTaken: Float, day: String)

    @Query("DELETE FROM daily_statistics_table")
    suspend fun deleteAllDailyStatistics()
}
