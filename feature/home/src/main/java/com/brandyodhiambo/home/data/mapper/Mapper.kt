package com.brandyodhiambo.home.data.mapper

import com.brandyodhiambo.common.domain.model.SleepTime
import com.brandyodhiambo.common.domain.model.WakeTime
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