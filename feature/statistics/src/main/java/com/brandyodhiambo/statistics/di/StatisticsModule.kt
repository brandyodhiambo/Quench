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
