package com.brandyodhiambo.settings.di

import com.brandyodhiambo.common.domain.repository.TimeFormateRepository
import com.brandyodhiambo.dao.ReminderModeDao
import com.brandyodhiambo.dao.TimeFormateDao
import com.brandyodhiambo.settings.data.repository.TimeFormateRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {
    @Provides
    @Singleton
    fun provideTimeFormateRepository(timeFormateDao: TimeFormateDao,reminderModeDao: ReminderModeDao):TimeFormateRepository{
        return TimeFormateRepositoryImpl(timeFormateDao,reminderModeDao)
    }
}