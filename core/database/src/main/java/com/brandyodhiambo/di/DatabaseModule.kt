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
}