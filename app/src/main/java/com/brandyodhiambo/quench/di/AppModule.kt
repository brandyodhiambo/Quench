package com.brandyodhiambo.quench.di

import android.content.Context
import androidx.room.Room
import com.brandyodhiambo.quench.data.local.database.QuenchDatabase
import com.brandyodhiambo.quench.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuenchDatabase(@ApplicationContext context: Context):QuenchDatabase{
        return Room.databaseBuilder(
            context,
            QuenchDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }
}