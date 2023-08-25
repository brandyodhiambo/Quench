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
package com.brandyodhiambo.statistics.di

import com.brandyodhiambo.common.domain.repository.DailyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.MonthlyStatisticsRepository
import com.brandyodhiambo.common.domain.repository.WeeklyStatisticRepository
import com.brandyodhiambo.dao.DailyStatisticsDao
import com.brandyodhiambo.dao.MonthlyStatisticsDao
import com.brandyodhiambo.dao.WeeklyStatisticDao
import com.brandyodhiambo.statistics.data.repository.DailyStatisticsRepositoryImpl
import com.brandyodhiambo.statistics.data.repository.MonthlyStatisticsRepositoryImpl
import com.brandyodhiambo.statistics.data.repository.WeeklyStatisticsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StatisticsModule {

    @Provides
    @Singleton
    fun provideDailyStatisticsRepository(dailyStatisticsDao: DailyStatisticsDao): DailyStatisticsRepository {
        return DailyStatisticsRepositoryImpl(dailyStatisticsDao = dailyStatisticsDao)
    }

    @Provides
    @Singleton
    fun provideWeeklyStatisticsRepository(weeklyStatisticDao: WeeklyStatisticDao): WeeklyStatisticRepository {
        return WeeklyStatisticsRepositoryImpl(weeklyStatisticDao = weeklyStatisticDao)
    }

    @Provides
    @Singleton
    fun provideMonthlyStatisticsRepository(monthlyStatisticsDao: MonthlyStatisticsDao): MonthlyStatisticsRepository {
        return MonthlyStatisticsRepositoryImpl(monthlyStatisticalDao = monthlyStatisticsDao)
    }
}
