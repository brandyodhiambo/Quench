package com.brandyodhiambo.statistics.data.mapper

import com.brandyodhiambo.common.domain.model.DailyStatistics
import com.brandyodhiambo.common.domain.model.MonthlyStatistics
import com.brandyodhiambo.common.domain.model.WeeklyStatistics
import com.brandyodhiambo.entity.DailyStatisticsEntity
import com.brandyodhiambo.entity.MonthlyStatisticsEntity
import com.brandyodhiambo.entity.WeeklyStatisticsEntity

internal fun DailyStatisticsEntity.toDailyStatistics(): DailyStatistics {
    return DailyStatistics(
        amountTaken = amountTaken,
        day = day,
    )
}

internal fun DailyStatistics.toDailyStatisticsEntity(): DailyStatisticsEntity {
    return DailyStatisticsEntity(
        amountTaken = amountTaken,
        day = day,
    )
}

internal fun WeeklyStatisticsEntity.toWeeklyStatistics(): WeeklyStatistics {
    return WeeklyStatistics(
        amountTaken = amountTaken,
        week = week,
    )
}

internal fun WeeklyStatistics.toWeeklyStatisticsEntity(): WeeklyStatisticsEntity {
    return WeeklyStatisticsEntity(
        amountTaken = amountTaken,
        week = week,
    )
}

internal fun MonthlyStatisticsEntity.toMonthlyStatistics(): MonthlyStatistics {
    return MonthlyStatistics(
        amountTaken = amountTaken,
        month = month,
    )
}

internal fun MonthlyStatistics.toMonthlyStatisticsEntity(): MonthlyStatisticsEntity {
    return MonthlyStatisticsEntity(
        amountTaken = amountTaken,
        month = month,
    )
}
