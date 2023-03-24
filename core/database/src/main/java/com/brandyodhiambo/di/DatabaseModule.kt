package com.brandyodhiambo.di

import android.content.Context
import androidx.room.Room
import com.brandyodhiambo.Constants.DATABASE_NAME
import com.brandyodhiambo.database.QuenchDatabase
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
    ): QuenchDatabase {
        return Room.databaseBuilder(
            context,
            QuenchDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
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
}