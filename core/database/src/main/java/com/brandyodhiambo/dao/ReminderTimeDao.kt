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
import com.brandyodhiambo.common.domain.model.Days
import com.brandyodhiambo.entity.ReminderTimeEntity

@Dao
interface ReminderTimeDao {
    @Insert
    suspend fun insertReminderTime(reminderTime: ReminderTimeEntity)

    @Query("SELECT *FROM reminder_table")
    fun getReminderTime(): LiveData<ReminderTimeEntity?>

    @Delete
    suspend fun deleteReminderTime(reminderTime: ReminderTimeEntity)

    @Query("UPDATE reminder_table SET hour = :hours, minute = :minutes,ampm=:ampm,isRepeated = :isRepeated, isAllDay=:isAllDay,days =:days WHERE id = :id")
    suspend fun updateReminderTime(
        id: Int,
        hours: String,
        minutes: String,
        ampm: String,
        isRepeated: Boolean,
        isAllDay: Boolean,
        days: List<Days>
    )

    @Query("DELETE FROM reminder_table")
    suspend fun deleteAllReminderTime()
}
