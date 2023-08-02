package com.brandyodhiambo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brandyodhiambo.converter.Converter
import com.brandyodhiambo.dao.DailyStatisticsDao
import com.brandyodhiambo.dao.GoalWaterIntakeDao
import com.brandyodhiambo.dao.IdealWaterIntakeDao
import com.brandyodhiambo.dao.LevelDao
import com.brandyodhiambo.dao.MonthlyStatisticsDao
import com.brandyodhiambo.dao.ReminderTimeDao
import com.brandyodhiambo.dao.SelectedDrinkDao
import com.brandyodhiambo.dao.SleepTimeDao
import com.brandyodhiambo.dao.WakeTimeDao
import com.brandyodhiambo.dao.WeeklyStatisticDao
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
    entities = [SelectedDrinkEntity::class, WakeTimeEntity::class, IdealWaterIntakeEntity::class, GoalWaterIntakeEntity::class, SleepTimeEntity::class, LevelEntity::class, ReminderTimeEntity::class, DailyStatisticsEntity::class, WeeklyStatisticsEntity::class, MonthlyStatisticsEntity::class],
    version = 4,
    exportSchema = false,
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
}
