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
        gson: Gson,
    ): QuenchDatabase {
        return Room.databaseBuilder(
            context,
            QuenchDatabase::class.java,
            DATABASE_NAME,
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
    fun provideGson(): Gson {
        return Gson()
    }
}
