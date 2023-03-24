package com.brandyodhiambo.home.di

import com.brandyodhiambo.common.domain.repository.IdealWaterIntakeRepository
import com.brandyodhiambo.common.domain.repository.SleepTimeRepository
import com.brandyodhiambo.common.domain.repository.WakeTimeRepository
import com.brandyodhiambo.dao.IdealWaterIntakeDao
import com.brandyodhiambo.dao.SleepTimeDao
import com.brandyodhiambo.dao.WakeTimeDao
import com.brandyodhiambo.home.data.repository.IdealWaterInkateRepositoryImpl
import com.brandyodhiambo.home.data.repository.SleepTimeRepositoryImpl
import com.brandyodhiambo.home.data.repository.WakeTimeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideSleepRepository(sleepTimeDao: SleepTimeDao): SleepTimeRepository {
        return SleepTimeRepositoryImpl(sleepTimeDao = sleepTimeDao)
    }

    @Provides
    @Singleton
    fun provideWakeTimeRepository(wakeTimeDao: WakeTimeDao):WakeTimeRepository{
        return WakeTimeRepositoryImpl(wakeTimeDao = wakeTimeDao)
    }

    @Provides
    @Singleton
    fun provideIdealWaterIntakeRepository(idealWaterIntakeDao: IdealWaterIntakeDao): IdealWaterIntakeRepository {
        return IdealWaterInkateRepositoryImpl(idealWaterIntakeDao = idealWaterIntakeDao)
    }


}