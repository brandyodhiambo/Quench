package com.brandyodhiambo.quench.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brandyodhiambo.quench.data.local.dao.GoalWaterIntakeDao
import com.brandyodhiambo.quench.data.local.dao.IdealWaterIntakeDao
import com.brandyodhiambo.quench.data.local.dao.LevelDao
import com.brandyodhiambo.quench.data.local.dao.ReminderTimeDao
import com.brandyodhiambo.quench.data.local.dao.ReportDao
import com.brandyodhiambo.quench.data.local.dao.SelectedDrinkDao
import com.brandyodhiambo.quench.data.local.dao.SleepTimeDao
import com.brandyodhiambo.quench.data.local.dao.WakeTimeDao
import com.brandyodhiambo.quench.data.local.entity.GoalWaterIntakeEntity
import com.brandyodhiambo.quench.data.local.entity.IdealWaterIntakeEntity
import com.brandyodhiambo.quench.data.local.entity.LevelEntity
import com.brandyodhiambo.quench.data.local.entity.ReminderTimeEntity
import com.brandyodhiambo.quench.data.local.entity.ReportEntity
import com.brandyodhiambo.quench.data.local.entity.SelectedDrinkEntity
import com.brandyodhiambo.quench.data.local.entity.SleepTimeEntity
import com.brandyodhiambo.quench.data.local.entity.WakeTimeEntity

@Database(
    entities = [IdealWaterIntakeEntity::class, LevelEntity::class, GoalWaterIntakeEntity::class,ReminderTimeEntity::class,ReportEntity::class,SelectedDrinkEntity::class,SleepTimeEntity::class,WakeTimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuenchDatabase :RoomDatabase(){
    abstract fun idealWaterIntakeDao(): IdealWaterIntakeDao
    abstract fun levelDao(): LevelDao
    abstract fun goalWaterIntakeDao(): GoalWaterIntakeDao
    abstract fun reminderTimeDao(): ReminderTimeDao
    abstract fun reportDao(): ReportDao
    abstract fun selectedDrinkDao(): SelectedDrinkDao
    abstract fun sleepTimeDao(): SleepTimeDao
    abstract fun wakeTimeDao(): WakeTimeDao
}