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
package com.brandyodhiambo.di

import android.content.Context
import androidx.room.Room
import com.brandyodhiambo.Constants.DATABASE_NAME
import com.brandyodhiambo.converter.Converter
import com.brandyodhiambo.database.QuenchDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuenchDatabase(
        @ApplicationContext context: Context,
        gson: Gson
    ): QuenchDatabase {
        return Room.databaseBuilder(
            context,
            QuenchDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converter(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideSleepTimeDao(database: QuenchDatabase) = database.sleepTimeDao()

    @Provides
    @Singleton
    fun provideWakeTimeDao(database: QuenchDatabase) = database.wakeTimeDao()

    @Provides
    @Singleton
    fun provideIdealWaterIntakeDao(database: QuenchDatabase) = database.idealWaterIntakeDao()

    @Provides
    @Singleton
    fun provideGoalWaterIntakeDao(database: QuenchDatabase) = database.goalWaterIntakeDao()

    @Provides
    @Singleton
    fun provideSelectedDrinkDao(database: QuenchDatabase) = database.selectedDrinkDao()

    @Provides
    @Singleton
    fun provideLevelDao(database: QuenchDatabase) = database.levelDao()

    @Provides
    @Singleton
    fun provideReminderTimeDao(database: QuenchDatabase) = database.reminderTimeDao()

    @Provides
    @Singleton
    fun provideDailyStatisticsDao(database: QuenchDatabase) = database.dailyStatisticsDao()

    @Provides
    @Singleton
    fun provideWeeklyStatisticsDao(database: QuenchDatabase) = database.weeklyStatisticsDao()

    @Provides
    @Singleton
    fun provideMonthlyStatisticsDao(database: QuenchDatabase) = database.monthlyStatisticsDao()

    @Provides
    @Singleton
    fun provideAchievementDao(database: QuenchDatabase) = database.achievementDao()

    @Provides
    @Singleton
    fun provideTimeFormateDao(database: QuenchDatabase) = database.timeFormateDao()

    @Provides
    @Singleton
    fun provideReminderModeDao(database:QuenchDatabase) = database.reminderModeDao()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
