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
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brandyodhiambo.entity.SleepTimeEntity

@Dao
interface SleepTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepTime(sleepTimeEntity: SleepTimeEntity)

    @Query("SELECT *FROM sleep_time_table")
    fun getSleepTime(): LiveData<SleepTimeEntity?>

    @Delete
    suspend fun deleteSleepTime(sleepTimeEntity: SleepTimeEntity)

    @Query("UPDATE sleep_time_table SET hour = :hours, minute = :minutes, ampm = :ampm WHERE id = :id")
    suspend fun updateSleepTime(id: Int, hours: String, minutes: String, ampm: String)

    @Query("DELETE FROM sleep_time_table")
    suspend fun deleteAllSleepTime()
}
