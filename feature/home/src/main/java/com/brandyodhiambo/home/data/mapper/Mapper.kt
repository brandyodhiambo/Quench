package com.brandyodhiambo.home.data.mapper

import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.entity.SleepTimeEntity

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