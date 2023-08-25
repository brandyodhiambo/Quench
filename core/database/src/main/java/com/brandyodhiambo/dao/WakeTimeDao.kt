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
import com.brandyodhiambo.entity.WakeTimeEntity

@Dao
interface WakeTimeDao {

    @Insert
    suspend fun insertWakeTime(wakeTimeEntity: WakeTimeEntity)

    @Query("SELECT *FROM wake_time_table")
    fun getWakeTime(): LiveData<WakeTimeEntity?>

    @Delete
    suspend fun deleteWakeTime(wakeTimeEntity: WakeTimeEntity)

    @Query("UPDATE wake_time_table SET hour = :hours, minute = :minutes, ampm = :ampm WHERE id = :id")
    suspend fun updateWakeTime(id: Int, hours: String, minutes: String, ampm: String)

    @Query("DELETE FROM wake_time_table")
    suspend fun deleteAllWakeTime()
}
