package com.brandyodhiambo.home.data.mapper

import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.entity.GoalWaterIntakeEntity
import com.brandyodhiambo.entity.IdealWaterIntakeEntity
import com.brandyodhiambo.entity.LevelEntity
import com.brandyodhiambo.entity.SelectedDrinkEntity
import com.brandyodhiambo.entity.SleepTimeEntity
import com.brandyodhiambo.entity.WakeTimeEntity

internal fun SleepTimeEntity.toSleepTime(): SleepTime {
    return SleepTime(
        hours = hour,
        minutes = minute,
        amPm = ampm
    )
}

internal fun SleepTime.toSleepTimeEntity(): SleepTimeEntity {
    return SleepTimeEntity(
        hour = hours,
        minute = minutes,
        ampm = amPm
    )
}

internal fun WakeTimeEntity.toWakeTime(): WakeTime {
    return WakeTime(
        hours = hour,
        minutes = minute,
        amPm = ampm
    )
}

internal fun WakeTime.toWakeTimeEntity(): WakeTimeEntity {
    return WakeTimeEntity(
        hour = hours,
        minute = minutes,
        ampm = amPm
    )
}

internal fun IdealWaterIntakeEntity.toIdealWaterIntake(): IdealWaterIntake {
    return IdealWaterIntake(
        waterIntake = waterIntake,
        form = form,
    )
}

internal fun IdealWaterIntake.toIdealWaterIntakeEntity(): IdealWaterIntakeEntity {
    return IdealWaterIntakeEntity(
        waterIntake = waterIntake,
        form = form,
    )
}

internal fun GoalWaterIntake.toGoalWaterIntakeEntity(): GoalWaterIntakeEntity {
    return GoalWaterIntakeEntity(
        waterIntake = waterIntake,
        form = form,
    )
}

internal fun GoalWaterIntakeEntity.toGoalWaterIntake(): GoalWaterIntake {
    return GoalWaterIntake(
        waterIntake = waterIntake,
        form = form,
    )
}

internal fun SelectedDrink.toSelectedDrinkEntity(): SelectedDrinkEntity {
    return SelectedDrinkEntity(
        drinkValue = drinkValue,
        icon = icon,
        time = time
    )
}

internal fun SelectedDrinkEntity.toSelectedDrink(): SelectedDrink {
    return SelectedDrink(
        drinkValue = drinkValue,
        icon = icon,
        time = time,
        id = id
    )
}

internal fun LevelEntity.toLevel(): Level {
    return Level(
        amountTaken = amountTaken,
        waterTaken = waterTaken,
    )
}

internal fun Level.toLevelEntity(): LevelEntity {
    return LevelEntity(
        amountTaken = amountTaken,
        waterTaken = waterTaken,
    )
}

