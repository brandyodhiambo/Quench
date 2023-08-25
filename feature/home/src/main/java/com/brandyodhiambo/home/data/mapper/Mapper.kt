/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brandyodhiambo.home.data.mapper

import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.model.GoalWaterIntake
import com.brandyodhiambo.common.domain.model.IdealWaterIntake
import com.brandyodhiambo.common.domain.model.Level
import com.brandyodhiambo.common.domain.model.ReminderTime
import com.brandyodhiambo.common.domain.model.SelectedDrink
import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
import com.brandyodhiambo.entity.AchievementEntity
import com.brandyodhiambo.entity.GoalWaterIntakeEntity
import com.brandyodhiambo.entity.IdealWaterIntakeEntity
import com.brandyodhiambo.entity.LevelEntity
import com.brandyodhiambo.entity.ReminderTimeEntity
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
        form = form
    )
}

internal fun IdealWaterIntake.toIdealWaterIntakeEntity(): IdealWaterIntakeEntity {
    return IdealWaterIntakeEntity(
        waterIntake = waterIntake,
        form = form
    )
}

internal fun GoalWaterIntake.toGoalWaterIntakeEntity(): GoalWaterIntakeEntity {
    return GoalWaterIntakeEntity(
        waterIntake = waterIntake,
        form = form
    )
}

internal fun GoalWaterIntakeEntity.toGoalWaterIntake(): GoalWaterIntake {
    return GoalWaterIntake(
        waterIntake = waterIntake,
        form = form
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
        waterTaken = waterTaken
    )
}

internal fun Level.toLevelEntity(): LevelEntity {
    return LevelEntity(
        amountTaken = amountTaken,
        waterTaken = waterTaken
    )
}

internal fun ReminderTimeEntity.toReminderTime(): ReminderTime {
    return ReminderTime(
        hour = hour,
        minute = minute,
        ampm = ampm,
        isRepeated = isRepeated,
        isAllDay = isAllDay,
        days = days
    )
}

internal fun ReminderTime.toReminderEntity(): ReminderTimeEntity {
    return ReminderTimeEntity(
        hour = hour,
        minute = minute,
        ampm = ampm,
        isRepeated = isRepeated,
        isAllDay = isAllDay,
        days = days
    )
}

internal fun AchievementEntity.toAchievement(): Achievement {
    return Achievement(
        isAchieved = isAchieved,
        day = day
    )
}

internal fun Achievement.toAchievementsEntity(): AchievementEntity {
    return AchievementEntity(
        isAchieved = isAchieved,
        day = day
    )
}
