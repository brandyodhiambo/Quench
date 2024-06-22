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
package com.brandyodhiambo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brandyodhiambo.converter.Converter
import com.brandyodhiambo.dao.AchievementDao
import com.brandyodhiambo.dao.DailyStatisticsDao
import com.brandyodhiambo.dao.GoalWaterIntakeDao
import com.brandyodhiambo.dao.IdealWaterIntakeDao
import com.brandyodhiambo.dao.LevelDao
import com.brandyodhiambo.dao.MonthlyStatisticsDao
import com.brandyodhiambo.dao.ReminderTimeDao
import com.brandyodhiambo.dao.SelectedDrinkDao
import com.brandyodhiambo.dao.SleepTimeDao
import com.brandyodhiambo.dao.TimeFormateDao
import com.brandyodhiambo.dao.WakeTimeDao
import com.brandyodhiambo.dao.WeeklyStatisticDao
import com.brandyodhiambo.entity.AchievementEntity
import com.brandyodhiambo.entity.DailyStatisticsEntity
import com.brandyodhiambo.entity.GoalWaterIntakeEntity
import com.brandyodhiambo.entity.IdealWaterIntakeEntity
import com.brandyodhiambo.entity.LevelEntity
import com.brandyodhiambo.entity.MonthlyStatisticsEntity
import com.brandyodhiambo.entity.ReminderTimeEntity
import com.brandyodhiambo.entity.SelectedDrinkEntity
import com.brandyodhiambo.entity.SleepTimeEntity
import com.brandyodhiambo.entity.WakeTimeEntity
import com.brandyodhiambo.entity.WeeklyStatisticsEntity

@TypeConverters(Converter::class)
@Database(
    entities = [SelectedDrinkEntity::class, WakeTimeEntity::class, IdealWaterIntakeEntity::class, GoalWaterIntakeEntity::class, SleepTimeEntity::class, LevelEntity::class, ReminderTimeEntity::class, DailyStatisticsEntity::class, WeeklyStatisticsEntity::class, MonthlyStatisticsEntity::class, AchievementEntity::class],
    version = 5,
    exportSchema = false
)
abstract class QuenchDatabase : RoomDatabase() {
    abstract fun wakeTimeDao(): WakeTimeDao
    abstract fun sleepTimeDao(): SleepTimeDao
    abstract fun selectedDrinkDao(): SelectedDrinkDao
    abstract fun idealWaterIntakeDao(): IdealWaterIntakeDao
    abstract fun goalWaterIntakeDao(): GoalWaterIntakeDao
    abstract fun levelDao(): LevelDao
    abstract fun reminderTimeDao(): ReminderTimeDao
    abstract fun dailyStatisticsDao(): DailyStatisticsDao
    abstract fun weeklyStatisticsDao(): WeeklyStatisticDao
    abstract fun monthlyStatisticsDao(): MonthlyStatisticsDao
    abstract fun achievementDao(): AchievementDao
    abstract fun timeFormateDao():TimeFormateDao
}
