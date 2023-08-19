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
package com.brandyodhiambo.statistics.data.mapper

import com.brandyodhiambo.common.domain.model.Achievement
import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.entity.AchievementEntity
import com.brandyodhiambo.entity.DailyStatisticsEntity
import com.brandyodhiambo.entity.MonthlyStatisticsEntity
import com.brandyodhiambo.entity.WeeklyStatisticsEntity

internal fun DailyStatisticsEntity.toDailyStatistics(): DailyStatistics {
    return DailyStatistics(
        amountTaken = amountTaken,
        day = day
    )
}

internal fun DailyStatistics.toDailyStatisticsEntity(): DailyStatisticsEntity {
    return DailyStatisticsEntity(
        amountTaken = amountTaken,
        day = day
    )
}

internal fun WeeklyStatisticsEntity.toWeeklyStatistics(): WeeklyStatistics {
    return WeeklyStatistics(
        amountTaken = amountTaken,
        week = week
    )
}

internal fun WeeklyStatistics.toWeeklyStatisticsEntity(): WeeklyStatisticsEntity {
    return WeeklyStatisticsEntity(
        amountTaken = amountTaken,
        week = week
    )
}

internal fun MonthlyStatisticsEntity.toMonthlyStatistics(): MonthlyStatistics {
    return MonthlyStatistics(
        amountTaken = amountTaken,
        month = month
    )
}

internal fun MonthlyStatistics.toMonthlyStatisticsEntity(): MonthlyStatisticsEntity {
    return MonthlyStatisticsEntity(
        amountTaken = amountTaken,
        month = month
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
